package com.charminseok.company.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/*
계약생성
1) 업체생성을 통해 업체 Master 정보가 생성된 업체에 한해 업체계약 생성이 가능
2) 업체계약 필수정보는 업체 ID, 계약시작일, 계약종료일
3) 계약 시작일은 계약생성 당일, 종료일은 D+365 일(1 년계약)으로 고정.
4) 업체별 계약기간이 중복되지 않도록 Validation
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractDomain {
    private Long contractId;
    private Long companyId;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
}
