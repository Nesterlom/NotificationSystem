package com.nesterlom.service;

import com.nesterlom.entity.Recipient;
import com.nesterlom.repository.RecipientRepo;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final RecipientRepo recipientRepo;

    public void uploadNewCsvFile(MultipartFile file){
        File temp = new File("D:\\Java\\Works\\Stud\\projects\\NotificationService\\src\\main\\resources\\files\\" + file.getOriginalFilename());

        Path path = Path.of(temp.getPath());

        try(Reader reader = Files.newBufferedReader(path)){
            List<Recipient> list = new CsvToBeanBuilder<Recipient>(reader)
                    .withType(Recipient.class)
                    .build()
                    .parse();

            for (Recipient r: list){
                r.setUserId(1);
            }

            recipientRepo.saveAll(list);
            //System.out.println(list.size());
            //System.out.println(list.get(0).getEmail());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addNewRecipients(MultipartFile file) {
        //TODO implement
    }

    public void deleteAll(){
        //TODO implement
    }
}
