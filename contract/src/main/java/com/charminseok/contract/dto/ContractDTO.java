package com.charminseok.contract.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
public class ContractDTO {
    private String companyName;
    private Long companyId;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
}
