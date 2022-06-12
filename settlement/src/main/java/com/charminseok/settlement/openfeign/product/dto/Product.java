package com.charminseok.settlement.openfeign.product.dto;

import lombok.Data;

@Data
public class Product {
    private Long productId;
    private String companyName;
    private String productName;
    private Integer price;
    private Integer stockCount;
}
