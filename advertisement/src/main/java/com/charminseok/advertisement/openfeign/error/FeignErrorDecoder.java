package com.charminseok.advertisement.openfeign.error;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        log.error("{} 요청이 성공하지 못했습니다. status : {}, body : {}", methodKey, response.status(), response.body());

        switch (response.status()) {
            case 400:
                break;
            case 404:
                if (methodKey.contains("getCompanyById")) {
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()), "No such company.");
                } else if (methodKey.contains("getContractByCompanyId")) {
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()), "No such contract.");
                } else if (methodKey.contains("getProductById")) {
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()), "No such product.");
                } else if (methodKey.contains("getProductList")) {
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()), "No such product.");
                }
            default:
                return new RuntimeException(methodKey + "해당 서비스가 원할하지 않습니다.");
        }
        return null;
    }
}
