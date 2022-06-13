package com.charminseok.advertisement.service;

import com.charminseok.advertisement.domain.AdvertisementDomain;
import com.charminseok.advertisement.domain.CPCTargetDomain;
import com.charminseok.advertisement.dto.CPCCountRequestDto;
import com.charminseok.advertisement.dto.CPCCountResponseDto;
import com.charminseok.advertisement.dto.RequestAdvertisement;
import com.charminseok.advertisement.dto.ResponseAdvertisement;
import java.util.List;

public interface AdvertisementService {
    AdvertisementDomain insertAdvertisement(RequestAdvertisement requestAdvertisement);

    List<ResponseAdvertisement> selectAdvertisementTop3List();

    CPCTargetDomain clickAdvertisement(Long advertisementId);

    AdvertisementDomain selectAdvertisement(Long advertisementId, RequestAdvertisement requestAdvertisement);

    List<CPCCountResponseDto> selectCPCCountList(CPCCountRequestDto cpcCountRequestDto);
}
