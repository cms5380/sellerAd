package com.charminseok.company.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompanyException extends RuntimeException {
    private final String message;
    private final String code;

    public CompanyException(CompanyErrorCode companyErrorCode){
        this.code = companyErrorCode.getCode();
        this.message = companyErrorCode.getMessage();
    }
}
