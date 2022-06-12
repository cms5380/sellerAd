package com.charminseok.company.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CompanyExceptionAdvice {
    @ExceptionHandler(CompanyException.class)
    public static ResponseEntity<ErrorForm> handleCompanyError(CompanyException e){
        return new ResponseEntity<>(new ErrorForm(e.getCode(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
