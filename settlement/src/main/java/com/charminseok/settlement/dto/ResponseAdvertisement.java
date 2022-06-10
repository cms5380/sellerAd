package com.charminseok.settlement.dto;

import lombok.Data;

@Data
public class ResponseAdvertisement {
    private Long advertisementId;
    private Long companyId;
    private Long productId;
    private int advertisementPrice;
}
