package com.charminseok.advertisement.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
public class RequestAdvertisement {
    @NotNull
    private Long companyId;
    @NotNull
    private Long productId;
    @NotNull
    @Max(value = 1000000)
    private Integer advertisementPrice;
}
