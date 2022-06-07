package com.charminseok.advertisement.mapper;


import com.charminseok.advertisement.domain.AdvertisementDomain;
import com.charminseok.advertisement.dto.RequestAdvertisement;
import com.charminseok.advertisement.dto.ResponseAdvertisement;
import com.charminseok.advertisement.dto.CPCTargetDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdvertisementMapper {
    void insertAdvertisement(RequestAdvertisement requestAdvertisement);

    List<ResponseAdvertisement> selectAdvertisementList();

    void insertCPCTarget(Long advertisementId);

    AdvertisementDomain selectAdvertisementById(Long advertisementId);
}
