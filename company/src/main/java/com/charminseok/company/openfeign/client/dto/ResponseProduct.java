package com.charminseok.company.openfeign.client.dto;

import lombok.Data;

@Data
public class ResponseProduct {
    private Long productId;
    private String companyName;
    private String productName;
    private Integer price;
    private Integer stockCount;
}
