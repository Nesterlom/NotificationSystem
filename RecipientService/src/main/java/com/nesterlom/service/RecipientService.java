package com.nesterlom.service;

import com.nesterlom.dto.RecipientsBatch;
import com.nesterlom.entity.Recipient;
import com.nesterlom.repository.RecipientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipientService {

    //private final NewTopic notificationTopic;

    private final KafkaTemplate<String, RecipientsBatch> kafkaTemplate;

    private final RecipientRepo recipientRepo;

    public String sendData() {
        List<Recipient> arr = new ArrayList<>();
        RecipientsBatch recipientsBatch = new RecipientsBatch();
        //recipientsBatch.setTemplateId(1);
        recipientsBatch.setTemplateName("firstTemplate");
        Recipient r1 = new Recipient();
        Recipient r2 = new Recipient();
        r1.setId(0);
        r1.setEmail("testerlom0@gmail.com");
        r1.setName("AA");
        r1.setSurname("EE");
        r1.setUserId(1);

        r2.setId(0);
        r2.setEmail("testerlom0@gmail.com");
        r2.setName("AA");
        r2.setSurname("EE");
        r2.setUserId(1);

        arr.add(r1);
        arr.add(r2);

        recipientsBatch.setRecipients(arr);

        kafkaTemplate.send("notificationTopic",0,null, recipientsBatch);

        return "Recipients were send";
    }


    //Take portion of 1000 recipients from DB
//    public String sendData() {
//
//        List<Recipient> arr;
//        RecipientsBatch recipientsBatch = new RecipientsBatch();
//        long numOfRows = recipientRepo.count();
//
//        double amountOfRecipientsInBatch = 100.0;
//
//        int numberOfOperations = (int)Math.ceil(numOfRows/amountOfRecipientsInBatch);
//
//        System.out.println("num of rows " + numOfRows);
//        System.out.println("Num of operations = " + numberOfOperations);
//
//        //recipientsBatch.setTemplateId(1);
//
//        //todo check how limit offset work(is last value is taken or not)
//        for (int i = 1; i <= numberOfOperations; i++) {
//            int limit = (int) (i * amountOfRecipientsInBatch);
//            int offset = (int) ((i - 1) * amountOfRecipientsInBatch);
//            arr = recipientRepo.findPortion2(limit, offset);
//            recipientsBatch.setRecipients(arr);
//            kafkaTemplate.send("notificationTopic",0,null, recipientsBatch);
//        }
//
//        return "Recipients were send";
//    }

//    public String sendDataWithFilters(Recipient recipient) {
//        ExampleMatcher customExampleMatcher = ExampleMatcher.matching()
//                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
//
//        Recipient exampleRecipient = new Recipient();
//        exampleRecipient.setName(recipient.getName());
//
//        Example<Recipient> example = Example.of(exampleRecipient, customExampleMatcher);
//        List<Recipient> arr = recipientRepo.findAll();
//
//        RecipientsBatch recipientsBatch = new RecipientsBatch();
//
//
//
//        long numOfRows = recipientRepo.count();
//
//        double amountOfRecipientsInBatch = 1000.0;
//
//        int numberOfOperations = (int)Math.ceil(numOfRows/amountOfRecipientsInBatch);
//
////        System.out.println("num of rows " + numOfRows);
////        System.out.println("Num of operations = " + numberOfOperations);
//
//        recipientsBatch.setTemplateId(1);
//
////        //todo check how limit offset work(is last value is taken or not)
////        for (int i = 1; i <= numberOfOperations; i++) {
////            int limit = (int) (i * amountOfRecipientsInBatch);
////            int offset = (int) ((i - 1) * amountOfRecipientsInBatch);
////            arr = recipientRepo.findPortion2(limit, offset);
////            recipientsBatch.setRecipients(arr);
////            kafkaTemplate.send("notificationTopic",0,null, recipientsBatch);
////        }
//
//        return "Recipients were send";
//    }
}


//        sending single recipient to Kafka
//        Recipient r = new Recipient();
//        r.setId(0);
//        r.setEmail("testerlom0@gmail.com");
//        r.setName("AA");
//        r.setSurname("EE");
//        r.setUserId(1);
//        Recipient[] arr2 = {r};
//        recipientsBatch.setRecipients(arr2);
//        kafkaTemplate.send("notificationTopic",0,null, recipientsBatch);
