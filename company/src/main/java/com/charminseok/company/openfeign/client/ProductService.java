package com.charminseok.company.openfeign.client;

import com.charminseok.company.dto.ResponseProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product")
public interface ProductService {
    @GetMapping("/product")
    ResponseProduct getProductByCompanyName(@RequestParam("company-name") String companyName);
}
