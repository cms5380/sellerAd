package com.charminseok.advertisement.service;


import com.charminseok.advertisement.domain.AdvertisementDomain;
import com.charminseok.advertisement.dto.RequestAdvertisement;
import com.charminseok.advertisement.dto.ResponseAdvertisement;
import com.charminseok.advertisement.dto.CPCTargetDTO;
import com.charminseok.advertisement.openfeign.company.dto.ResponseCompany;
import com.charminseok.advertisement.openfeign.company.service.CompanyService;
import com.charminseok.advertisement.openfeign.company.dto.ResponseContract;
import com.charminseok.advertisement.mapper.AdvertisementMapper;
import com.charminseok.advertisement.openfeign.product.dto.ResponseProduct;
import com.charminseok.advertisement.openfeign.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdvertisementService {
    private final AdvertisementMapper advertisementMapper;
    private final CompanyService companyService;
    private final ProductService productService;

    public void bidAdvertisement(RequestAdvertisement requestAdvertisement){
        ResponseCompany companyById = companyService.getCompanyById(requestAdvertisement.getCompanyId());
        if(companyById == null){
            throw new RuntimeException("입력한 회사가 없습니다.");
        }
        List<ResponseProduct> productList = productService.getProductList();

        ResponseContract contractByCompanyId = companyService.getContractByCompanyId(requestAdvertisement.getCompanyId());
        if(contractByCompanyId.isValidContract()){
            advertisementMapper.insertAdvertisement(requestAdvertisement);
        } else {
            throw new RuntimeException("계약이 올바르지 않습니다.");
        }

    }

    public List<ResponseAdvertisement> getAdvertisementList(){
        List<ResponseProduct> productList = productService.getProductList();
        List<ResponseAdvertisement> responseAdvertisements = advertisementMapper.selectAdvertisementList();

        List<ResponseAdvertisement> collect = responseAdvertisements.stream()
                .filter(ad -> productList.stream()
                            .anyMatch(responseProduct -> {
                                if(responseProduct.getProductId().equals(ad.getProductId())){
                                    ad.setProductName(responseProduct.getProductName());
                                    ad.setProductPrice(responseProduct.getPrice());
                                    return true;
                                }
                                return false;
                            }))
                .sorted(Comparator.comparing(ResponseAdvertisement::getAdvertisementPrice, Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toList());

        collect.stream().map(ad -> productList.stream().filter(product -> product.getProductId().equals(ad.getProductId())));
        return collect;
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
