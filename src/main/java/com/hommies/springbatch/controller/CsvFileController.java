package com.hommies.springbatch.controller;

import com.hommies.springbatch.model.FileDetail;
import com.hommies.springbatch.repository.FileDetailRepository;
import com.hommies.springbatch.service.CSVService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/api/v1/csvprocessing")
@RequiredArgsConstructor
public class CsvFileController {

    private final CSVService service;


    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@ModelAttribute("file") MultipartFile file) {
        return new ResponseEntity<>(service.uploadFile(file), HttpStatus.OK)  ;

    }


    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
      return new ResponseEntity<>(service.deleteFile(fileName), HttpStatus.OK)  ;

    }

}

