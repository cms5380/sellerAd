package com.charminseok.advertisement.openfeign.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseProduct {
    private Long productId;
    private String companyName;
    private String productName;
    private Integer price;
    private Integer stockCount;

    public boolean isEmpty() {
        return this.productId == null
                && this.companyName == null
                && this.productName == null
                && this.price == null
                && this.stockCount == null;
    }

    public void updateValue(ResponseProduct responseProduct) {
        if(responseProduct.getProductName() != null){
            this.productName = responseProduct.getProductName();
        }
        if(responseProduct.getStockCount() != null){
            this.stockCount = responseProduct.getStockCount();
        }
        if(responseProduct.getPrice() != null){
            this.price = responseProduct.getPrice();
        }
    }

}
