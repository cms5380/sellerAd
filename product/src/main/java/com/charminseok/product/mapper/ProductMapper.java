package com.charminseok.product.mapper;

import com.charminseok.product.domain.ProductDomain;
import com.charminseok.product.dto.RequestProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductDomain selectProductByCompanyName(String companyName);

    ProductDomain selectProductByProductId(
            @Param("requestProduct") RequestProduct requestProduct
    );

    List<ProductDomain> selectProductList(int stockCount, int start, int pageSize);

    void insertProduct(ProductDomain productDomain);
}
