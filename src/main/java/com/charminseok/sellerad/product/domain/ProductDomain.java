package com.charminseok.sellerad.product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDomain {
    private Long productId;
    private String companyName;
    private String productName;
    private Integer price;
    private Integer stockCount;
}
