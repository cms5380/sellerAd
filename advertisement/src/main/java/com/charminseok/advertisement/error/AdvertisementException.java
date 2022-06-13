package com.charminseok.advertisement.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdvertisementException extends RuntimeException {
    private final String message;
    private final String code;

    public AdvertisementException(AdvertisementErrorCode advertisementErrorCode){
        this.code = advertisementErrorCode.getCode();
        this.message = advertisementErrorCode.getMessage();
    }
}
