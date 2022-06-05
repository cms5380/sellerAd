package com.charminsek.advertisement.service;

import com.charminsek.advertisement.domain.AdvertisementDomain;
import com.charminsek.advertisement.dto.AdvertisementDTO;
import com.charminsek.advertisement.dto.AdvertisementResponse;
import com.charminsek.advertisement.dto.CPCTargetDTO;
import com.charminsek.advertisement.mapper.AdvertisementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertisementService {
    private final AdvertisementMapper advertisementMapper;

    public void bidAdvertisement(AdvertisementDTO advertisementDTO){
        advertisementMapper.insertAdvertisement(advertisementDTO);
    }

    public List<AdvertisementResponse> getAdvertisementList(){
        return advertisementMapper.selectAdvertisementList();
    }

    public AdvertisementDomain getAdvertisement(Long advertisementId){
        return advertisementMapper.selectAdvertisementById(advertisementId);
    }

    public void clickAdvertisement(Long advertisementId) {
        AdvertisementDomain advertisement = getAdvertisement(advertisementId);

        CPCTargetDTO cpcTargetDTO = CPCTargetDTO.builder()
                .advertisementId(advertisementId)
                .advertisementPrice(advertisement.getAdvertisementPrice())
                .build();

        advertisementMapper.insertCPCTarget(cpcTargetDTO);
    }
}
