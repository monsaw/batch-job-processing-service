package com.hommies.springbatch.controller;

import com.hommies.springbatch.exception.FileException;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {

    private final JobLauncher jobLauncher;
    private final Job job;

    private static final String UPLOAD_DIR = "src/main/resources/uploaded-csv";


    @PostMapping
    public ResponseEntity<?> importCsvToDBJob(){
        Path filePath = Paths.get(UPLOAD_DIR, "student.csv");

        // Check if the file exists
        File file = filePath.toFile();

        if(!file.exists()) {
            throw new FileException("file to process is missing");
        }

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();

        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException |
                 JobRestartException |
                 JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    return new ResponseEntity<>("File processed successfully", HttpStatus.OK);
    }





//     @Scheduled(fixedRate = 24 * 60 * 60 * 1000) // every 24 hours start counting at when the app launch

//    @Scheduled(cron = "0 54 15 * * *") // at exact 15:52



}
