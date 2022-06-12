package com.charminseok.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductUpdateDto {
    private String companyName;
    private String productName;
    private Integer price;
    private Integer stockCount;
}
