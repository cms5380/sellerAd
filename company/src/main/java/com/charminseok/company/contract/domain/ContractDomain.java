package com.charminseok.company.contract.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractDomain {
    private Long contractId;
    private Long companyId;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
}
