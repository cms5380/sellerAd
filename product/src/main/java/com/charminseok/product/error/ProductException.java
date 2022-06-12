package com.charminseok.product.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductException extends RuntimeException {
    private final String message;
    private final String code;

    public ProductException(ProductErrorCode productErrorCode){
        this.code = productErrorCode.getCode();
        this.message = productErrorCode.getMessage();
    }
}
