package com.charminseok.product.mapper;

import com.charminseok.product.domain.ProductDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductDomain selectProductByCompanyName(
            @Param("companyName") String companyName
    );

    List<ProductDomain> selectProductList();
}
