package com.amadeusz.library.infrastructure;

import com.amadeusz.library.application.exceptions.CustomerNotFoundException;
import com.amadeusz.library.application.exceptions.IllegalOperationException;
import com.amadeusz.library.application.exceptions.NoInRepositoryException;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import springfox.documentation.annotations.ApiIgnore;

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

    @ExceptionHandler(NoInRepositoryException.class)
    public ResponseEntity<ExceptionResponse> response(NoInRepositoryException ex){
        ExceptionResponse exRe = new ExceptionResponse();
        exRe.setError(ex.getClass().getSimpleName());
        exRe.setDescription(ex.getMessage());
        return ResponseEntity.badRequest().body(exRe);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ExceptionResponse> response(CustomerNotFoundException ex){
        ExceptionResponse exRe = new ExceptionResponse();
        exRe.setError(ex.getClass().getSimpleName());
        exRe.setDescription(ex.getMessage());
        return ResponseEntity.badRequest().body(exRe);
    }

    @ExceptionHandler(IllegalOperationException.class)
    public ResponseEntity<ExceptionResponse> response(IllegalOperationException ex){
        ExceptionResponse exRe = new ExceptionResponse();
        exRe.setError(ex.getClass().getSimpleName());
        exRe.setDescription(ex.getMessage());
        return ResponseEntity.badRequest().body(exRe);
    }


}
