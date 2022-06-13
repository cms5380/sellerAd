package com.charminseok.settlement.openfeign.error;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class FeignClientAdvice {
    @ExceptionHandler(FeignException.class)
    public static ResponseEntity<ErrorForm> handleComapnyError(FeignException e){
        log.error(e.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FeignClientException.class)
    public static ResponseEntity<ErrorForm> FeignClientExceptionHandler(FeignClientException ex) {
        ex.printStackTrace();
        log.error("feign error status: " + ex.getStatus());
        if(ex.getStatus() == 500){
            ex.setErrorForm(new ErrorForm("500", "서버 상태가 좋지 않습니다."));
        }

        return new ResponseEntity<>(ex.getErrorForm(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}