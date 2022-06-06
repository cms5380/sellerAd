package com.charminseok.advertisement.dto;

import lombok.Data;

import javax.validation.constraints.Max;

@Data
public class RequestAdvertisement {
    private Long companyId;
    private Long productId;
    @Max(value = 1000000)
    private Integer advertisementPrice;
}
