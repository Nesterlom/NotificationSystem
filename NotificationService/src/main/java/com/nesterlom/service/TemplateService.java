package com.nesterlom.service;

import com.nesterlom.entity.Recipient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class TemplateService {

    private final Path rootPath = Paths.get("D:\\Java\\Works\\Stud\\projects\\projects2\\NotificationSystem\\NotificationService\\src\\main\\resources\\templates\\");
    private final String path = "D:\\Java\\Works\\Stud\\projects\\projects2\\NotificationSystem\\NotificationService\\src\\main\\resources\\templates\\";

    public String[] getAllTemplateNames() {
        File file = new File(path);

        return file.list();
    }

    public String getTemplateByName(String templateName) {
        return templateName;
    }

    //todo change IllegalArgument to custom exception. See spring guide on file uploading
    public void uploadNewTemplate(MultipartFile file){
        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("Failed to store empty file.");
            }

            String newPath = path + file.getOriginalFilename();
            Path destinationFile = Paths.get(newPath);

            if (!destinationFile.getParent().equals(rootPath.toAbsolutePath())) {
                throw new IllegalArgumentException(
                        "Cannot store file outside templates directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new IllegalArgumentException("Failed to store file.", e);
        }
    }

    public boolean deleteTemplate(String templateName){
        String templatePath = path + templateName + ".html";
        File savedFile = new File(templatePath);
        return savedFile.delete();
    }

}
