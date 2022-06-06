package com.charminseok.advertisement.service;


import com.charminseok.advertisement.domain.AdvertisementDomain;
import com.charminseok.advertisement.dto.RequestAdvertisement;
import com.charminseok.advertisement.dto.ResponseAdvertisement;
import com.charminseok.advertisement.dto.CPCTargetDTO;
import com.charminseok.advertisement.feignclient.company.dto.ResponseCompany;
import com.charminseok.advertisement.feignclient.company.service.CompanyService;
import com.charminseok.advertisement.feignclient.contract.dto.ResponseContract;
import com.charminseok.advertisement.feignclient.contract.service.ContractService;
import com.charminseok.advertisement.mapper.AdvertisementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertisementService {
    private final AdvertisementMapper advertisementMapper;
    private final CompanyService companyService;
    private final ContractService contractService;

    public void bidAdvertisement(RequestAdvertisement requestAdvertisement){
        ResponseCompany companyById = companyService.getCompanyById(requestAdvertisement.getCompanyId());
        if(companyById == null){
            throw new RuntimeException("입력한 회사가 없습니다.");
        }

        ResponseContract contractByCompanyId = contractService.getContractByCompanyId(requestAdvertisement.getCompanyId());
        if(contractByCompanyId.isValidContract()){
            advertisementMapper.insertAdvertisement(requestAdvertisement);
        } else {
            throw new RuntimeException("계약이 올바르지 않습니다.");
        }

    }

    public List<ResponseAdvertisement> getAdvertisementList(){
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
