package com.charminseok.settlement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SettlementDomain {
    private String clickDate;
    private Long companyId;
    private String companyName;
    private Long advertisementId;
    private Long productId;
    private String productName;
    private Integer clickCount;
    private Integer totalBillAmount;
}
