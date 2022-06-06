package com.charminseok.advertisement.dto;

import lombok.Data;

@Data
public class ResponseAdvertisement {
    private Long advertisementId;
    private Long companyId;
    private Long productId;
    private String productName;
    private Integer productPrice;
    private Integer advertisementPrice;
}
