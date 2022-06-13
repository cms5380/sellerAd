package com.charminseok.product.error;

import lombok.Getter;

@Getter
public enum ProductErrorCode {
    NO_SUCH_PRODUCT("E1000000001", "없는 상품입니다."),
    NOT_EXISTS_COMPANY("E1000000002", "상품에 셋팅된 없체가 아닙니다."),
    INSERT_ERROR("E1000000003", "상품 입력에 실패했습니다.");

    private final String code;
    private final String message;

    ProductErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
