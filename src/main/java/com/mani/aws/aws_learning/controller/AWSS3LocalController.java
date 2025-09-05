package com.mani.aws.aws_learning.controller;

import com.mani.aws.aws_learning.service.AWSS3LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/awss3local")
public class AWSS3LocalController {

    @Autowired
    private AWSS3LocalService awsService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException, IOException {
        awsService.uploadFile(file);
        return ResponseEntity.ok("File uploaded successfully!");
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable String filename) {
        byte[] data = awsService.downloadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .body(data);
    }
}
