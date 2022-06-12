package com.charminseok.advertisement.service;


import com.charminseok.advertisement.domain.AdvertisementDomain;
import com.charminseok.advertisement.domain.CPCTargetDomain;
import com.charminseok.advertisement.dto.CPCCountRequestDto;
import com.charminseok.advertisement.dto.CPCCountResponseDto;
import com.charminseok.advertisement.dto.RequestAdvertisement;
import com.charminseok.advertisement.dto.ResponseAdvertisement;
import com.charminseok.advertisement.openfeign.company.dto.ResponseCompany;
import com.charminseok.advertisement.openfeign.company.service.CompanyService;
import com.charminseok.advertisement.openfeign.company.dto.ResponseContract;
import com.charminseok.advertisement.mapper.AdvertisementMapper;
import com.charminseok.advertisement.openfeign.product.cache.ProductCacheService;
import com.charminseok.advertisement.openfeign.product.dto.ProductDto;
import com.charminseok.advertisement.openfeign.product.dto.ResponseProduct;
import com.charminseok.advertisement.openfeign.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdvertisementService {
    private final AdvertisementMapper advertisementMapper;
    private final CompanyService companyService;
    private final ProductService productService;
    private final ProductCacheService productCacheService;

    public AdvertisementDomain insertAdvertisement(RequestAdvertisement requestAdvertisement) {
        ResponseCompany companyById = companyService.getCompanyById(requestAdvertisement.getCompanyId());
        if (companyById == null) {
            throw new RuntimeException("입력한 회사가 없습니다.");
        }


        ResponseContract contractByCompanyId = companyService.getContractByCompanyId(requestAdvertisement.getCompanyId());
        if (!contractByCompanyId.isValidContract()) {
            throw new RuntimeException("계약이 올바르지 않습니다.");
        }

        ResponseProduct product = productService.getProductById(requestAdvertisement.getProductId(), ProductDto.builder()
                .productId(requestAdvertisement.getProductId())
                .companyName(companyById.getCompanyName())
                .build());
        if (product == null) {
            throw new RuntimeException("없는 상품입니다.");
        }

        AdvertisementDomain advertisementDomain = AdvertisementDomain.builder()
                .companyId(requestAdvertisement.getCompanyId())
                .productId(requestAdvertisement.getProductId())
                .advertisementPrice(requestAdvertisement.getAdvertisementPrice())
                .build();

        if (advertisementMapper.insertAdvertisement(advertisementDomain) == 1) {
            return advertisementDomain;
        } else {
            return new AdvertisementDomain();
        }

    }

    public List<ResponseAdvertisement> selectAdvertisementTop3List() {
        int pageSize = 100000;
        Long advertisementTotalCount = advertisementMapper.getAdvertisementTotalCount();
        List<ResponseAdvertisement> items = new ArrayList<>();

        for (int start = 0; start < advertisementTotalCount / pageSize + 1; start++) {
            List<ResponseAdvertisement> responseAdvertisements = advertisementMapper.selectAdvertisementList(start * pageSize, pageSize);

            for (ResponseAdvertisement adv : responseAdvertisements) {
                ResponseProduct product = productCacheService.getProductCache(adv.getProductId());
                if (productCacheService.isProductEmpty(product)) {
                    product = productService.getProductById(adv.getProductId(), new ProductDto());
                    productCacheService.updateProductCache(product.getProductId(), product);
                }

                if (product.getStockCount() == 0) {
                    continue;
                }

                adv.setProductName(product.getProductName());
                adv.setProductPrice(product.getPrice());
                items.add(adv);

                if (items.size() == 3) {
                    return items;
                }
            }
        }

        return items;
    }


    public CPCTargetDomain clickAdvertisement(Long advertisementId) {
        CPCTargetDomain cpcTargetDomain = CPCTargetDomain.builder()
                .advertisementId(advertisementId)
                .build();

        if (advertisementMapper.insertCPCTarget(cpcTargetDomain) == 1) {
            return cpcTargetDomain;
        } else {
            return new CPCTargetDomain();
        }
    }

    public AdvertisementDomain selectAdvertisement(Long advertisementId, RequestAdvertisement requestAdvertisement) {

        return advertisementMapper.selectAdvertisement(advertisementId, requestAdvertisement);
    }


    public List<CPCCountResponseDto> selectCPCCountList(CPCCountRequestDto cpcCountRequestDto) {
        return advertisementMapper.selectCPCCountList(cpcCountRequestDto);
    }
}
