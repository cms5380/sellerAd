package com.charminseok.product.service;

import com.charminseok.product.domain.ProductDomain;
import com.charminseok.product.dto.RequestPaging;
import com.charminseok.product.dto.RequestProduct;

import java.util.List;

public interface ProductService {

    List<ProductDomain> getProductList(RequestProduct requestProduct, RequestPaging requestPaging);

    void setProduct(ProductDomain productDomain);

    ProductDomain getProductByProductId(RequestProduct requestProduct);

    ProductDomain getProduct(RequestProduct requestProduct);
}
