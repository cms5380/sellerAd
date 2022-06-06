package com.charminseok.company.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ContractDTO {
    private String companyName;
    private Long companyId;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
}
