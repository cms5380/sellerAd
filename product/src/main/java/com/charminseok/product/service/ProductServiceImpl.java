package com.charminseok.product.service;

import com.charminseok.product.domain.ProductDomain;
import com.charminseok.product.dto.RequestPaging;
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
    public List<ProductDomain> getProductList(RequestProduct requestProduct, RequestPaging requestPaging) {
        List<ProductDomain> productDomains = productMapper.selectProductList(requestProduct, requestPaging);
//        streamBridge.send("productList", productDomains);
        return productDomains;
    }

    @Override
    public void setProduct(ProductDomain productDomain) {
        productMapper.insertProduct(productDomain);
    }

    @Override
    public ProductDomain getProduct(RequestProduct requestProduct) {
        return productMapper.selectProduct(requestProduct);
    }

    @Override
    public ProductDomain getProductByProductId(RequestProduct requestProduct) {
        return productMapper.selectProductByProductId(requestProduct);
    }
}
