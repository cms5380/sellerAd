package com.charminseok.advertisement.openfeign.company.service;

import com.charminseok.advertisement.openfeign.company.dto.ResponseCompany;
import com.charminseok.advertisement.openfeign.company.dto.ResponseContract;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "company")
public interface CompanyService {
    @GetMapping("/company/{companyId}")
    ResponseCompany getCompanyById(@PathVariable("companyId") Long companyId);

    @GetMapping("/contract/{companyId}")
    ResponseContract getContractByCompanyId(@PathVariable Long companyId);
}
