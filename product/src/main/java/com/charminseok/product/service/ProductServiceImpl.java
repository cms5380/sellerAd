package com.charminseok.product.service;

import com.charminseok.product.domain.ProductDomain;
import com.charminseok.product.dto.ProductCreateDto;
import com.charminseok.product.dto.ProductUpdateDto;
import com.charminseok.product.dto.Paging;
import com.charminseok.product.dto.RequestProduct;
import com.charminseok.product.mapper.ProductMapper;
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
        List<ProductDomain> productDomains = productMapper.selectProductList(requestProduct, paging);
        return productDomains;
    }

    @Override
    public void setProduct(ProductCreateDto productCreateDto) {
        productMapper.insertProduct(productCreateDto);
    }

    @Override
    public ProductDomain getProduct(Long productId, RequestProduct requestProduct) {
        return productMapper.selectProduct(ProductDomain.builder()
                .productId(productId)
                .productName(requestProduct.getProductName())
                .companyName(requestProduct.getCompanyName())
                .stockCount(requestProduct.getStockCount())
                .build());
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
