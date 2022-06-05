package com.charminsek.advertisement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CPCTargetDTO {
    private Long advertisementId;
    private LocalDateTime ClickDateTime;
    private int advertisementPrice;
}
