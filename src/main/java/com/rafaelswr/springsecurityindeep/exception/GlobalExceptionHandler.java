package com.rafaelswr.springsecurityindeep.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DocumentException.class)
    public ResponseEntity<String> handle(DocumentException doc){
        return new ResponseEntity<>(doc.getMessage(), HttpStatus.NOT_FOUND);
    }
}
