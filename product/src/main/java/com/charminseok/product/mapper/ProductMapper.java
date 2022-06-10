package com.charminseok.product.mapper;

import com.charminseok.product.domain.ProductDomain;
import com.charminseok.product.dto.RequestPaging;
import com.charminseok.product.dto.RequestProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductDomain selectProductByProductId(
            @Param("requestProduct") RequestProduct requestProduct
    );

    List<ProductDomain> selectProductList(
            @Param("requestProduct") RequestProduct requestProduct,
            @Param("requestPaging") RequestPaging requestPaging);

    void insertProduct(ProductDomain productDomain);

    ProductDomain selectProduct(
            @Param("requestProduct") RequestProduct requestProduct
    );
}
