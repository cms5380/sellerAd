package com.charminseok.advertisement.openfeign.product.cache;

import com.charminseok.advertisement.openfeign.product.dto.ResponseProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductCacheService {
    private static final ResponseProduct EMPTY_PRODUCT = new ResponseProduct();

    @Cacheable(cacheNames = "product", key = "#productId")
    public ResponseProduct getProductCache(final Long productId) {
        log.info("cache miss.");
        return EMPTY_PRODUCT;
    }

    @CachePut(cacheNames = "product", key = "#productId")
    public ResponseProduct updateProductCache(final Long productId, final ResponseProduct responseProduct){
        log.info("cache update.");

        return ResponseProduct.builder()
                .productId(productId)
                .companyName(responseProduct.getCompanyName())
                .productName(responseProduct.getProductName())
                .stockCount(responseProduct.getStockCount())
                .price(responseProduct.getPrice())
                .build();
    }

    @CacheEvict(cacheNames = "product", key = "#productId")
    public boolean expireProductCache(final Long productId){
        return true;
    }

    public boolean isProductEmpty(final ResponseProduct product){
        return product.isEmpty();
    }
}
