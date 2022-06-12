package com.charminseok.advertisement.dto;

import lombok.Data;

@Data
public class CPCCountResponseDto {
    private Long advertisementId;
    private Integer clickCount;
    private Long companyId;
    private Long productId;
    private Integer advertisementPrice;
}
