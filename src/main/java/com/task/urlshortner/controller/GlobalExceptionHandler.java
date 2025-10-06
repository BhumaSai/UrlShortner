package com.task.urlshortner.controller;

import com.task.urlshortner.exception.ErrorHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ErrorHandler.class)
    public ResponseEntity<Map<String,String>> error(ErrorHandler ex){
        Map<String,String> body = new HashMap<>();
        body.put("TimeStamps", String.valueOf(LocalDateTime.now()));
        body.put("message",ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
