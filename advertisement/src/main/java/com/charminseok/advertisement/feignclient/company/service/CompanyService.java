package com.charminseok.advertisement.feignclient.company.service;

import com.charminseok.advertisement.feignclient.company.dto.ResponseCompany;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "company-service")
public interface CompanyService {
    @GetMapping("/company-service/company")
    ResponseCompany getCompanyById(@RequestParam("companyId") Long companyId);
}
