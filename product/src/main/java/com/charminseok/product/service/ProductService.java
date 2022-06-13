package com.charminseok.product.service;

import com.charminseok.product.domain.ProductDomain;
import com.charminseok.product.dto.ProductCreateDto;
import com.charminseok.product.dto.ProductUpdateDto;
import com.charminseok.product.dto.Paging;
import com.charminseok.product.dto.RequestProduct;

import java.util.List;

public interface ProductService {

    List<ProductDomain> getProductList(RequestProduct requestProduct, Paging paging);

    ProductDomain insertProduct(ProductCreateDto productCreateDto);

    ProductDomain getProduct(Long productId, RequestProduct requestProduct);

    ProductDomain getProductByCompanyName(String companyName);

    ProductDomain updateProduct(Long productId, ProductUpdateDto productUpdateDto);
}
