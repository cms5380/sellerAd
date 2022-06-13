package com.charminseok.company.openfeign.error;

import java.util.Collection;
import java.util.Map;


public class FeignClientException extends RuntimeException{
    private final int status;
    private final String errorMessage;
    private final Map<String, Collection<String>> headers;
    private ErrorForm errorForm;


    public FeignClientException(Integer status, String errorMessage, Map<String, Collection<String>> headers
            , ErrorForm errorForm) {
        super(errorMessage);
        this.status = status;
        this.errorMessage = errorMessage;
        this.headers = headers;
        this.errorForm = errorForm;
    }

    public Integer getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Map<String, Collection<String>> getHeaders() {
        return headers;
    }

    public ErrorForm getErrorForm() {
        return errorForm;
    }

    public void setErrorForm(ErrorForm errorForm){
        this.errorForm = errorForm;
    }
}