package com.charminseok.settlement.openfeign.advertisement.dto;

import lombok.Data;

@Data
public class CPCResponseDto {
    private Long advertisementId;
    private Integer clickCount;
    private Long companyId;
    private Long productId;
    private Integer advertisementPrice;
}
