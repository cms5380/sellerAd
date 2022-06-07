package com.charminseok.advertisement.openfeign.company.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponseContract {
    private Long contractId;
    private Long companyId;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;

    public boolean isValidContract(){
        return LocalDate.now().isBefore(this.contractEndDate) && (LocalDate.now().isAfter(this.contractStartDate) || LocalDate.now().isEqual(this.contractStartDate));
    }
}
