package com.charminseok.product.mapper;

import com.charminseok.product.domain.ProductDomain;
import com.charminseok.product.dto.ProductCreateDto;
import com.charminseok.product.dto.Paging;
import com.charminseok.product.dto.RequestProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductDomain> selectProductList(
            @Param("requestProduct") RequestProduct requestProduct,
            @Param("paging") Paging paging);

    int insertProduct(ProductDomain productDomain);

    ProductDomain selectProduct(
            @Param("productDomain") ProductDomain productDomain
    );

    int updateProduct(ProductDomain productDomain);

    ProductDomain selectProductByCompanyName(String companyName);
}
