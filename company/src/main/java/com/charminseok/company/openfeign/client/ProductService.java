package com.charminseok.company.openfeign.client;

import com.charminseok.company.dto.ResponseProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product")
public interface ProductService {
    @GetMapping("/product/{companyName}")
    ResponseProduct getProductByCompanyName(@PathVariable String companyName);
}
