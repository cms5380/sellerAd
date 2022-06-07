package com.charminseok.advertisement.openfeign.product.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestProduct {
    private Long productId;
    private String companyName;
}
