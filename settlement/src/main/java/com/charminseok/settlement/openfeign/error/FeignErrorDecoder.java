package com.charminseok.settlement.openfeign.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import feign.codec.StringDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final StringDecoder stringDecoder = new StringDecoder();

    @Override
    public FeignClientException decode(String methodKey, Response response) {
        String message = null;
        ErrorForm errorForm = null;
        if (response.body() != null) {
            try {
                message = stringDecoder.decode(response, String.class).toString();
                errorForm = objectMapper.readValue(message, ErrorForm.class);
            } catch (IOException e) {
                log.error(methodKey + "Error Deserializing response body from failed feign request response.", e);
            }
        }
        return new FeignClientException(response.status(), message, response.headers(), errorForm);
    }
}