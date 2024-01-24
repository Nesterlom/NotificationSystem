package com.nesterlom.controller;

import com.nesterlom.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/template")
public class TemplateController {

    private final TemplateService templateService;

    @GetMapping
    @ResponseBody
    public String[] getAllTemplateNames(){
        return templateService.getAllTemplateNames();
    }

    @GetMapping("/{templateName}")
    public String getTemplateByName(@PathVariable String templateName){
        return templateService.getTemplateByName(templateName);
    }

    @PostMapping
    @ResponseBody
    public void uploadNewTemplate(@RequestParam("file")MultipartFile file){
        templateService.uploadNewTemplate(file);
    }

    @DeleteMapping("/{templateName}")
    @ResponseBody
    public boolean deleteTemplate(@PathVariable String templateName){
        return templateService.deleteTemplate(templateName);
    }
}
