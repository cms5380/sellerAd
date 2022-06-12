package com.charminseok.advertisement.mapper;


import com.charminseok.advertisement.domain.AdvertisementDomain;
import com.charminseok.advertisement.domain.CPCTargetDomain;
import com.charminseok.advertisement.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdvertisementMapper {
    int insertAdvertisement(AdvertisementDomain advertisementDomain);

    List<ResponseAdvertisement> selectAdvertisementList(int start, int pageSize);

    int insertCPCTarget(CPCTargetDomain cpcTargetDomain);

    Long getAdvertisementTotalCount();

    AdvertisementDomain selectAdvertisement(
            @Param("advertisementId") Long advertisementId,
            @Param("requestAdvertisement") RequestAdvertisement requestAdvertisement
    );


    List<CPCCountResponseDto> selectCPCCountList(CPCCountRequestDto cpcCountRequestDto);
}
