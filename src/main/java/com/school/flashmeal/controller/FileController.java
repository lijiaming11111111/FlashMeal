package com.school.flashmeal.controller;

import com.school.flashmeal.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("D:\\浏览器下载")
    private String uploadPath;

    @PostMapping
    public Result<String> upload(@RequestParam MultipartFile file){
        String originalFilename=file.getOriginalFilename();
        originalFilename=originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        String fileName = UUID.randomUUID()+originalFilename;
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File dest = new File(uploadPath,fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String url = "/uploads/"+fileName;
        return Result.success(url);
    }
}
