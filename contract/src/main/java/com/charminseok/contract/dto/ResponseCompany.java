package com.charminseok.contract.dto;

import lombok.Data;

@Data
public class ResponseCompany {
    private Long companyId;
    private String companyName;
    private Long companyBusinessNumber;
    private String companyTel;
    private String companyAddress;
}
