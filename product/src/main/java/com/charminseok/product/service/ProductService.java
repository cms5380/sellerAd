package com.charminseok.product.service;

import com.charminseok.product.domain.ProductDomain;

import java.util.List;

public interface ProductService {
    List<ProductDomain> getProductList(int stockCount);

    void setProduct(ProductDomain productDomain);
    ProductDomain getProductByCompanyName(String companyName);

    ProductDomain getProductByProductId(Long productId);
}
