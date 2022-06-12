package com.charminseok.company.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CompanyInsertDto {
    private Long companyId;
    private String companyName;
    @Min(value = 1000000000L) @Max(value = 9999999999L)
    private Long companyBusinessNumber;
    @Pattern(regexp = "\\d{11}", message = "번호가 올바르지 않습니다.")
    private String companyTel;
    private String companyAddress;
}
