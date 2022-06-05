package com.charminsek.advertisement.mapper;

import com.charminsek.advertisement.domain.AdvertisementDomain;
import com.charminsek.advertisement.dto.AdvertisementDTO;
import com.charminsek.advertisement.dto.AdvertisementResponse;
import com.charminsek.advertisement.dto.CPCTargetDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdvertisementMapper {
    void insertAdvertisement(AdvertisementDTO advertisementDTO);

    List<AdvertisementResponse> selectAdvertisementList();

    void insertCPCTarget(CPCTargetDTO cpcTargetDTO);

    AdvertisementDomain selectAdvertisementById(Long advertisementId);
}
