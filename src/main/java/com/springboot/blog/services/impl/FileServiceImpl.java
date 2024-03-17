package com.springboot.blog.services.impl;

import com.springboot.blog.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        String fileName = randomId.concat(name.substring(name.lastIndexOf(".")));
        String filePath = path + "/" + fileName;
        File f = new File(path);
        if(!f.exists())
            f.mkdir();
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws IOException {
        String filePath = path+"/"+fileName;
        InputStream inputStream = new FileInputStream(filePath);
        return inputStream;
    }
}
