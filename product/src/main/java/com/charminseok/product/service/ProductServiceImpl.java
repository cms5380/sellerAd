package com.charminseok.product.service;

import com.charminseok.product.domain.ProductDomain;
import com.charminseok.product.dto.RequestProduct;
import com.charminseok.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductMapper productMapper;

    public List<ProductDomain> getProductList(int stockCount, int start, int pageSize) {
        return productMapper.selectProductList(stockCount, start, pageSize);
    }

    public void setProduct(ProductDomain productDomain) {
        productMapper.insertProduct(productDomain);
    }

    public ProductDomain getProductByCompanyName(String companyName) {
        return productMapper.selectProductByCompanyName(companyName);
    }

    public ProductDomain getProductByProductId(RequestProduct requestProduct) {
        return productMapper.selectProductByProductId(requestProduct);
    }
}
