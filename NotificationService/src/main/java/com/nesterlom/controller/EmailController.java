package com.nesterlom.controller;

import com.nesterlom.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;


@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/test")
    public String test(){
        return "Works";
    }

    @GetMapping("/testTemplate")
    public String testTemplate(){
        try {
            emailService.testTemplate();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "Ok";
    }

    @GetMapping("/send")
    public String sendSingleNotification(){
       // emailService.sendNotification();
        return "Send";
    }

    @GetMapping("/sends")
    public String sendNotifications(){
//        try {
//            emailService.sendNotifications();
//        } catch (MessagingException e) {
//            System.out.println("WRONG SENDS");
//            throw new RuntimeException(e);
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }
        return "Send all";
    }
}
