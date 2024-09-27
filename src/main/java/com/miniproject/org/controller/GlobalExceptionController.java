package com.miniproject.org.controller;

import com.miniproject.org.exception.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(value = TransactionException.class)
    public ResponseEntity<String> getException(TransactionException transactionException){
        return new ResponseEntity<>(transactionException.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
