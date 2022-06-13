package com.charminseok.advertisement.error;

import lombok.Getter;

@Getter
public enum AdvertisementErrorCode {
    INSERT_ERROR("E3000000001", "광고입찰 생성에 실패했습니다.");

    private final String code;
    private final String message;

    AdvertisementErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
