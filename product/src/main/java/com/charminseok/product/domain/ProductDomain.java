package com.charminseok.product.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDomain {
    private Long productId;
    private String companyName;
    private String productName;
    private Integer price;
    private Integer stockCount;
}
