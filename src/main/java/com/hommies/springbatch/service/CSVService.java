package com.hommies.springbatch.service;

import com.hommies.springbatch.exception.FileException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class CSVService {
    private static final String UPLOAD_DIR = "src/main/resources/uploaded-csv";


    public String uploadFile(MultipartFile file){
        if (file.isEmpty()) {
            throw new FileException("File is empty");
        }

        String fileName = file.getOriginalFilename();

        if (!isValidFileName(fileName)) {
            throw new FileException("Invalid file name");
        }

        try {
            // Create the directory if it doesn't exist
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Save the file to the specified directory
            Path filePath = Path.of(uploadDir.getAbsolutePath(), fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return "File uploaded successfully";
        } catch (IOException e) {
            throw new FileException("Failed to upload file");
        }
    }


    public String deleteFile(String fileName){

        if (!isValidFileName(fileName)) {
            throw new FileException("Invalid file name,");
        }

        try {
            // Construct the file path
            Path filePath = Paths.get(UPLOAD_DIR, fileName);

            File file = filePath.toFile();
            if (file.exists()) {

                if (file.delete()) {
                    return "file successfully deleted";
                } else {
                    return "Failed to delete file";
                }
            } else {
                throw new FileException("File not found");
            }
        } catch (Exception e) {
            throw new FileException(e.getMessage());
        }
    }


    private boolean isValidFileName(String fileName) {

        return fileName.equalsIgnoreCase("student.csv");
    }
}
