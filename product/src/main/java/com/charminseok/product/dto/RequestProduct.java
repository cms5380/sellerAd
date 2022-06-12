package com.charminseok.product.dto;

import lombok.Data;

@Data
public class RequestProduct {
    private String productName;
    private String companyName;
    private Integer stockCount;
}
