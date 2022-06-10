package com.charminseok.product.service;

import com.charminseok.product.domain.ProductDomain;
import com.charminseok.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductMapper productMapper;

    public List<ProductDomain> getProductList(int stockCount) {
        return productMapper.selectProductList(stockCount);
    }

    public void setProduct(ProductDomain productDomain) {
        productMapper.insertProduct(productDomain);
    }

    public ProductDomain getProductByCompanyName(String companyName) {
        return productMapper.selectProductByCompanyName(companyName);
    }

    public ProductDomain getProductByProductId(Long productId) {
        return productMapper.selectProductByProductId(productId);
    }
}
