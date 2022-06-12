package com.charminseok.company.domain;

import lombok.*;

/*
업체생성
1) 상품에 셋팅된 업체의 경우만 등록 가능 (최초등록시 업체명 기준 체크)
à 등록 후 상품정보와의 연계성을 가진 업체명을 제외한 업체정보 update 가능
(상품정보의 업체명 컬럼을 FK 로 설정한 것은 테스트를 위해 의도된 컨셉임)
2) 업체필수정보는 업체 ID (PK 10 자리), 업체명, 사업자번호(10 자리), 업체휴대폰번호, 업체주소
3) 사업자번호, 업체전화번호 등의 Data 셋팅 시 자릿수, 숫자여부만 Validation
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDomain {
    private Long companyId;
    private String companyName;
    private Long companyBusinessNumber;
    private String companyTel;
    private String companyAddress;
}
