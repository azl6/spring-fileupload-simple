package com.azl6.fileupload.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azl6.fileupload.responses.UploadResponse;
import com.azl6.fileupload.services.FileStorageService;

@RestController
public class FileUploadController {

  private final FileStorageService fileStorageService;

  public FileUploadController(FileStorageService fileStorageService) {
    this.fileStorageService = fileStorageService;
  }

  @PostMapping("/upload")
  public ResponseEntity<UploadResponse> uploadFile(
      @RequestParam(name = "file", required = false) MultipartFile file
  ) {
      String fileName = fileStorageService.storeFile(file);

      UploadResponse uploadResponse = new UploadResponse(fileName);

      return ResponseEntity.ok().body(uploadResponse);
  }

  @GetMapping("/hello")
  public ResponseEntity<String> helloWorld(@RequestParam String personName){
    return ResponseEntity.ok().body("Hello, " + personName);
  }
}
