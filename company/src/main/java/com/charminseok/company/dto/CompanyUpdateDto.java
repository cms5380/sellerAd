package com.charminseok.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyUpdateDto {
    private String companyName;
    private Long companyBusinessNumber;
    private String companyTel;
    private String companyAddress;
}
