package com.charminseok.company.contract.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ContractResponseDto {
    private Long contractId;
    private Long companyId;
    private String companyName;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
}
