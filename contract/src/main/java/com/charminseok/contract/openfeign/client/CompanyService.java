package com.charminseok.contract.openfeign.client;

import com.charminseok.contract.dto.ResponseCompany;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "company-service")
public interface CompanyService {

    @GetMapping("/company-service/company/{companyName}")
    ResponseCompany getCompanyByCompanyName(@PathVariable String companyName);

    @GetMapping("/company-service/hello")
    String companyHello();

}