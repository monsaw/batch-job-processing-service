package com.hommies.springbatch.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(FileException.class)
    public ResponseEntity<ResponseDto> handlerForFileException(final FileException e){
        ResponseDto response = new ResponseDto();
        response.setMessage(e.getMessage());
        response.setStatus(HttpStatus.FORBIDDEN);
        response.setStatusCode(HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
    }
}
