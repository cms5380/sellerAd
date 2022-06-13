package com.charminseok.product.service.impl;

import com.charminseok.product.domain.ProductDomain;
import com.charminseok.product.dto.ProductCreateDto;
import com.charminseok.product.dto.ProductUpdateDto;
import com.charminseok.product.dto.Paging;
import com.charminseok.product.dto.RequestProduct;
import com.charminseok.product.error.ProductErrorCode;
import com.charminseok.product.error.ProductException;
import com.charminseok.product.mapper.ProductMapper;
import com.charminseok.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final StreamBridge streamBridge;

    @Override
    public List<ProductDomain> getProductList(RequestProduct requestProduct, Paging paging) {
        return productMapper.selectProductList(requestProduct, paging);
    }

    @Override
    public ProductDomain insertProduct(ProductCreateDto productCreateDto) {
        ProductDomain productDomain = ProductDomain.builder()
                .companyName(productCreateDto.getCompanyName())
                .productName(productCreateDto.getProductName())
                .price(productCreateDto.getPrice())
                .stockCount(productCreateDto.getStockCount())
                .build();
        if(productMapper.insertProduct(productDomain) == 1){
            return productDomain;
        } else {
            throw new ProductException(ProductErrorCode.INSERT_ERROR);
        }
    }

    @Override
    public ProductDomain getProduct(Long productId, RequestProduct requestProduct) {
        ProductDomain product = ProductDomain.builder()
                .productId(productId)
                .productName(requestProduct.getProductName())
                .companyName(requestProduct.getCompanyName())
                .stockCount(requestProduct.getStockCount())
                .build();
        product = productMapper.selectProduct(product);
        if(product == null){
            throw new ProductException(ProductErrorCode.NO_SUCH_PRODUCT);
        }

        return product;
    }

    @Override
    public ProductDomain getProductByCompanyName(String companyName) {
        ProductDomain product = productMapper.selectProductByCompanyName(companyName);
        if(product == null){
            throw new ProductException(ProductErrorCode.NOT_EXISTS_COMPANY);
        }

        return product;
    }

    @Override
    public ProductDomain updateProduct(Long productId, ProductUpdateDto productUpdateDto) {
        ProductDomain productDomain = ProductDomain.builder()
                .productId(productId)
                .companyName(productUpdateDto.getCompanyName())
                .productName(productUpdateDto.getProductName())
                .price(productUpdateDto.getPrice())
                .stockCount(productUpdateDto.getStockCount())
                .build();


        if(productMapper.updateProduct(productDomain) == 1){
            streamBridge.send("productUpdate-out-0", productDomain);
        }

        return productDomain;
    }
}
