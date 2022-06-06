package com.charminseok.advertisement.openfeign.product.service;

import com.charminseok.advertisement.openfeign.product.dto.ResponseProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "product")
public interface ProductService {

    @GetMapping("/Product")
    List<ResponseProduct> getProductList();
}
