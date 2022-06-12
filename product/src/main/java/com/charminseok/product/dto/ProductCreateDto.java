package com.charminseok.product.dto;

import lombok.Getter;

@Getter
public class ProductCreateDto {
    private String companyName;
    private String productName;
    private Integer price;
    private Integer stockCount;
}