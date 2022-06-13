package com.charminseok.advertisement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CPCTargetDomain {
    private Long cpcId;
    private Long advertisementId;
    private LocalDateTime clickDatetime;
}
