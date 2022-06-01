package com.charminseok.sellerad;

import lombok.Data;

@Data
public class ProductDomain {
    private Long productId;
    private Long companyId;
    private String productName;
    private String price;
    private Integer stockCount;
}
