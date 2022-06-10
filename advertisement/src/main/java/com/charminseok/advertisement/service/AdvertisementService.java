package com.charminseok.advertisement.service;


import com.charminseok.advertisement.domain.AdvertisementDomain;
import com.charminseok.advertisement.dto.RequestAdvertisement;
import com.charminseok.advertisement.dto.ResponseAdvertisement;
import com.charminseok.advertisement.dto.CPCTargetDTO;
import com.charminseok.advertisement.openfeign.company.dto.ResponseCompany;
import com.charminseok.advertisement.openfeign.company.service.CompanyService;
import com.charminseok.advertisement.openfeign.company.dto.ResponseContract;
import com.charminseok.advertisement.mapper.AdvertisementMapper;
import com.charminseok.advertisement.openfeign.product.dto.RequestProduct;
import com.charminseok.advertisement.openfeign.product.dto.ResponseProduct;
import com.charminseok.advertisement.openfeign.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
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

        ResponseProduct product = productService.getProductById(requestAdvertisement.getProductId(), RequestProduct.builder()
                .productId(requestAdvertisement.getProductId())
                .build());
        if(product == null){
            throw new RuntimeException("없는 상품입니다.");
        }

        ResponseContract contractByCompanyId = companyService.getContractByCompanyId(requestAdvertisement.getCompanyId());
        if(contractByCompanyId.isValidContract()){
            advertisementMapper.insertAdvertisement(requestAdvertisement);
        } else {
            throw new RuntimeException("계약이 올바르지 않습니다.");
        }

    }

    public List<ResponseAdvertisement> getAdvertisementList(){
        int pageSize = 100000;
        Long advertisementTotalCount = advertisementMapper.getAdvertisementTotalCount();
        List<ResponseAdvertisement> items = new ArrayList<>();
        int validItemCount = 0;
        Set<Long> productNoStock = Collections.newSetFromMap(new ConcurrentHashMap<>());
        for(int start = 0; start < advertisementTotalCount; start += pageSize){
            List<ResponseAdvertisement> responseAdvertisements = advertisementMapper.selectAdvertisementList(start, pageSize);

            for(ResponseAdvertisement adv : responseAdvertisements){
                if(productNoStock.contains(adv.getProductId())){
                    continue;
                }

                ResponseProduct productById = productService.getProductById(adv.getProductId(), RequestProduct.builder()
                        .productId(adv.getProductId())
                        .stockCount(1)
                        .build());

                if(productById == null) {
                    productNoStock.add(adv.getProductId());
                    continue;
                }

                adv.setProductName(productById.getProductName());
                adv.setProductPrice(productById.getPrice());
                items.add(adv);
                validItemCount++;

                if(validItemCount == 3){
                    break;
                }
            }

            if(validItemCount == 3){
                break;
            }
        }

        return items;
    }

    public AdvertisementDomain getAdvertisement(Long advertisementId){
        return advertisementMapper.selectAdvertisementById(advertisementId);
    }

    public void clickAdvertisement(Long advertisementId) {
        advertisementMapper.insertCPCTarget(advertisementId);
    }
}
