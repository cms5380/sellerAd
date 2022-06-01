package com.charminseok.sellerad.company.dto;

import lombok.Data;

@Data
public class CompanyDTO {
    private Long companyId;
    private String companyName;
    private Long companyBusinessNumber;
    private String companyTel;
    private String companyAddress;
}
