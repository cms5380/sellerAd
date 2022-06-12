package com.charminseok.product.error;

import lombok.Getter;

@Getter
public enum ProductErrorCode {
    NO_SUCH_PRODUCT("E001", "없는 상품입니다."),
    NOT_EXISTS_COMPANY("E002", "상품에 셋팅된 없체가 아닙니다.");

    private final String code;
    private final String message;

    ProductErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
