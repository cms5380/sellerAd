package com.charminsek.advertisement.domain;

import lombok.Getter;
import lombok.Setter;

/*
광고입찰
1) 계약생성 및 계약기간이 유효한 업체에 한해 광고 입찰이 가능
2) 광고입찰 필수정보는 광고입찰 ID(PK 10 자리), 업체 ID, 상품 ID(10 자리), 광고입찰가
3) 입력된 상품 ID 가 광고입찰한 업체가 등록한 상품 ID 인지 Validation
4) 최대 광고입찰가는 1,000,000 원으로 제한
 */
@Setter
@Getter
public class AdvertisementDomain {
    private Long advertisementId;
    private Long companyId;
    private Long productId;
    private int advertisementPrice;

}
