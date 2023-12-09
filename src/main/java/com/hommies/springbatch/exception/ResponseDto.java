package com.hommies.springbatch.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ResponseDto {
    private String message;
    private int statusCode;
    private HttpStatus status;
    private LocalDateTime localDateTime = LocalDateTime.now();

}
