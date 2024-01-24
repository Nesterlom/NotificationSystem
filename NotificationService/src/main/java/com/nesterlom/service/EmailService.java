package com.nesterlom.service;

import com.nesterlom.dto.RecipientsBatch;
import com.nesterlom.entity.Recipient;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class EmailService {

    //@Autowired
    private final JavaMailSender emailSender;

    private final SpringTemplateEngine templateEngine;

    private final MailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

//    @Autowired
//    private ServletWebServerApplicationContext webServerContext;

    private SimpleMailMessage emailMessage;

//    {
//        emailMessage.setText("Test test test");
//    }

    //private final RecipientRepo recipientRepo;

    //private final JavaMailSender javaMailSender;

    //private final KafkaTemplate kafkaTemplate;

    @KafkaListener(topics = "notificationTopic", groupId = "notificationsGroup")
    public void kafkaTest(RecipientsBatch batch) throws MessagingException {
        int size = batch.getRecipients().size();

        MimeMessage[] emails = new MimeMessage[size];

        Context context = new Context();

        for (int i = 0; i < size; i++) {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            Recipient r = batch.getRecipients().get(i);

            helper.setTo(r.getEmail());
            helper.setSubject("Hi from Nesterlom");

            context.setVariable("name", r.getName());
            context.setVariable("surname", r.getSurname());
            context.setVariable("id", r.getId());
            String emailContent = templateEngine.process(batch.getTemplateName(), context);
            helper.setText(emailContent, true);

            emails[i] = mimeMessage;
        }

        emailSender.send(emails);
    }

    public void testTemplate() throws MessagingException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo("testerlom0@gmail.com");
        helper.setSubject("TEST TEST");

        String message = "THIS MESSAGE I GET FROM HTML BABY";
        String template = "template";

        Context context = new Context();
        context.setVariable("message", message);
        String emailContent = templateEngine.process(template, context);

        helper.setText(emailContent, true);

        emailSender.send(mimeMessage);
    }

    //todo check if it neccessary to create entire new message

//    @KafkaListener(topics = "notificationTopic", groupId = "notificationsGroup")
//    public void kafkaTest(RecipientsBatch batch) {
//        int size = batch.getRecipients().length;
//
//        SimpleMailMessage[] emails = new SimpleMailMessage[batch.getRecipients().length];
//
//        for (int i = 0; i < size; i++) {
//            System.out.println("Rep ID = " + batch.getRecipients()[i].getId());
//
//            emailMessage = new SimpleMailMessage();
//            emailMessage.setText("Test test test");
//
//            emailMessage.setTo(batch.getRecipients()[i].getEmail());
//            emailMessage.setSubject(String.valueOf(batch.getRecipients()[i].getEmail()));
//            emails[i] = emailMessage;
//        }
//
//        mailSender.send(emails);
//    }
}

//todo save message to db and check if it was send
//todo attempt to resend message if smt wrong
//todo feature of making message form templates and store this templates in db too

//todo send Method throws an MailException,
// so I need to handle situation if message
// is not send properly and try again

//    @KafkaListener(topics = "notificationTopic", groupId = "notificationsGroup")
//    public void kafkaTest(RecipientsBatch batch) {
//        //int size = batch.getRecipients().length;
//        int size = batch.getRecipients().size();
//
//        String templateText = templateRepo.findTemplateTextById(batch.getTemplateId());
//        String text;
//
////        SimpleMailMessage[] emails = new SimpleMailMessage[batch.getRecipients().length];
//        SimpleMailMessage[] emails = new SimpleMailMessage[size];
//
//        for (int i = 0; i < size; i++) {
//            Recipient r = batch.getRecipients().get(i);
//            text = String.format(templateText, r.getName(), r.getSurname(), r.getId());
//
//            emailMessage = new SimpleMailMessage();
//
//            emailMessage.setText(text);
//            emailMessage.setTo(r.getEmail());
//            emailMessage.setSubject(String.valueOf(r.getEmail()));
//
//            emails[i] = emailMessage;
//
//        }
//
//        mailSender.send(emails);
//    }