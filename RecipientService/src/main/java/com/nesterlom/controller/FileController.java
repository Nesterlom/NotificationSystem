package com.nesterlom.controller;

import com.nesterlom.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    //All methods should work with current registered user(Principle)
    @PostMapping("/upload")
    //Replace all old recipients to new
    public void uploadNewCsv(@RequestParam("file")MultipartFile file){
        fileService.uploadNewCsvFile(file);
    }

    @PostMapping("/add")
    //Add new to old
    public void addNewRecipientsFromCSV(@RequestParam("file")MultipartFile file){
        fileService.addNewRecipients(file);
    }

    @PostMapping()
    //Delete all
    public void deleteAllData(){
        fileService.deleteAll();
    }
}
