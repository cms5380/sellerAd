package com.charminseok.settlement.openfeign.advertisement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CPCRequestDto {
    private String startDate;
    private String endDate;
}