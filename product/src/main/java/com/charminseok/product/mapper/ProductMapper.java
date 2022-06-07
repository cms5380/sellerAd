package com.charminseok.product.mapper;

import com.charminseok.product.domain.ProductDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductDomain selectProductByCompanyName(String companyName);

    ProductDomain selectProductByProductId(Long productId);

    List<ProductDomain> selectProductList(int stockCount);

    void insertProduct(ProductDomain productDomain);
}
