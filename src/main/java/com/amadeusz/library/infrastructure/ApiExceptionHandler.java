package com.amadeusz.library.infrastructure;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> response(IllegalArgumentException ex){
        ExceptionResponse exRe = new ExceptionResponse();
        exRe.setError(ex.getClass().getSimpleName());
        exRe.setDescription(ex.getMessage());
        return ResponseEntity.badRequest().body(exRe);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<ExceptionResponse> response(HttpMessageConversionException ex){
        ExceptionResponse exRe = new ExceptionResponse();
        exRe.setError(ex.getClass().getSimpleName());
        exRe.setDescription(ex.getMessage());
        return ResponseEntity.badRequest().body(exRe);
    }

}
