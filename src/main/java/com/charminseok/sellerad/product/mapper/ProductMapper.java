package com.charminseok.sellerad.product.mapper;

import com.charminseok.sellerad.product.domain.ProductDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper {

    ProductDomain selectProductByCompanyName(
            @Param("companyName") String companyName
    );
}
