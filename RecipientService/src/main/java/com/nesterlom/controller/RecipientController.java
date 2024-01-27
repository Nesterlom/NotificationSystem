package com.nesterlom.controller;

import com.nesterlom.service.RecipientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipient")
public class RecipientController {

    private final RecipientService recipientService;

    @GetMapping("/test")
    public String test(){
        return "STRING RETURNED rep";
    }

    @GetMapping("/sendAll")
    public String sendAllRecipientsInKafka(){
        return recipientService.sendData();
    }

    @GetMapping("/send")
    public void sendOneRecipientToKafka(){

    }

    @GetMapping
    public String getAllRecipients(){
        return "OK";
    }

    @PostMapping
    public void addRecipient(){

    }

    @PutMapping
    public void updateRecipient(){

    }

    @DeleteMapping
    public void deleteRecipient(){

    }
}
