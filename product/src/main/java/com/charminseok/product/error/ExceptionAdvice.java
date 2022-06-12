package com.charminseok.product.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {
    @ExceptionHandler(ProductException.class)
    public static ResponseEntity<ErrorForm> handleComapnyError(ProductException e){
        return new ResponseEntity<>(new ErrorForm(e.getCode(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
