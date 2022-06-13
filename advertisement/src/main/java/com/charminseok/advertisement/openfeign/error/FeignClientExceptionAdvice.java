package com.charminseok.advertisement.openfeign.error;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class FeignClientExceptionAdvice {
    @ExceptionHandler(FeignException.class)
    public static ResponseEntity<ErrorForm> handleComapnyError(FeignException e){
        log.error(e.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FeignClientException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorForm FeignClientExceptionHandler(FeignClientException ex) {
        ex.printStackTrace();
        log.error("feign error status: " + ex.getStatus());

        return ex.getErrorForm();
    }
}