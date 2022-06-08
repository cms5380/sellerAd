package com.charminseok.advertisement.openfeign.error;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()){
            case 400:
                break;
            case 404:
                if (methodKey.contains("getCompanyById")){
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()), "No such company.");
                }

                if (methodKey.contains("getContractByCompanyId")){
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()), "No such contract.");
                }

                if (methodKey.contains("getProductById")){
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()), "No such product.");
                }

                if (methodKey.contains("getProductList")){
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()), "No such product.");
                }
            default:
                return new Exception(response.reason());
        }
        return null;
    }
}
