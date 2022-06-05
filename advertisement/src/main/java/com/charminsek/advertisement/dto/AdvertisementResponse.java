package com.charminsek.advertisement.dto;

import lombok.Data;

@Data
public class AdvertisementResponse {
    private Long advertisementId;
    private Long companyId;
    private Long productId;
    private String productName;
    private Integer productPrice;
    private Integer advertisementPrice;
}
