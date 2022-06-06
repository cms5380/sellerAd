package com.charminseok.product.service;

import com.charminseok.product.domain.ProductDomain;
import com.charminseok.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;

    public ProductDomain getProductByCompanyName(String companyName){
        return productMapper.selectProductByCompanyName(companyName);
    }

    public List<ProductDomain> getProductList() {
        return productMapper.selectProductList();
    }
}
