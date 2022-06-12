package com.charminseok.settlement.openfeign.product.service;

import com.charminseok.settlement.openfeign.product.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product")
public interface ProductService {
    @GetMapping("/product/{productId}")
    Product getProduct(@PathVariable("productId") Long productId);
}
