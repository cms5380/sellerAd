package com.charminseok.advertisement.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CPCTargetDomain {
    private Long CPCId;
    private Long advertisementId;
    private LocalDateTime ClickDateTime;
    private int advertisementPrice;
}
