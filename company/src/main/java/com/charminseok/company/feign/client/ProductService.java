package com.charminseok.company.feign.client;

import com.charminseok.company.dto.ResponseProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductService {
    @GetMapping("/product-service/product/{companyName}")
    ResponseProduct getProductByCompanyName(@PathVariable String companyName);
}
