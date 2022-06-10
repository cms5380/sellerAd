package com.charminseok.advertisement.openfeign.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestProduct {
    private Long productId;
    private String companyName;
    private Integer stockCount;
}
