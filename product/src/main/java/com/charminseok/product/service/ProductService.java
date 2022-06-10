package com.charminseok.product.service;

import com.charminseok.product.domain.ProductDomain;
import com.charminseok.product.dto.RequestProduct;

import java.util.List;

public interface ProductService {
    List<ProductDomain> getProductList(int stockCount, int start, int pageSize);

    void setProduct(ProductDomain productDomain);
    ProductDomain getProductByCompanyName(String companyName);

    ProductDomain getProductByProductId(RequestProduct requestProduct);
}
