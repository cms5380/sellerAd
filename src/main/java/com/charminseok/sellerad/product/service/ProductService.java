package com.charminseok.sellerad.product.service;

import com.charminseok.sellerad.product.domain.ProductDomain;
import com.charminseok.sellerad.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;
    public ProductDomain getProductByCompanyName(String companyName){
        return productMapper.selectProductByCompanyName(companyName);
    }
}
