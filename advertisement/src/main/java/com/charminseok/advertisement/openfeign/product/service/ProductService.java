package com.charminseok.advertisement.openfeign.product.service;

import com.charminseok.advertisement.openfeign.product.dto.ResponseProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "product")
public interface ProductService {

    @GetMapping("/products")
    List<ResponseProduct> getProductList(@RequestParam("stock-count") int stockCount);

    @GetMapping("/product/{productId}")
    ResponseProduct getProductById(@PathVariable Long productId);
}
