package com.charminseok.advertisement.openfeign.company.service;

import com.charminseok.advertisement.openfeign.company.dto.ResponseCompany;
import com.charminseok.advertisement.openfeign.company.dto.ResponseContract;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "company")
public interface CompanyService {
    @GetMapping("/company")
    ResponseCompany getCompanyById(@RequestParam("companyId") Long companyId);

    @GetMapping("/company/{companyId}/contract")
    ResponseContract getContractByCompanyId(@PathVariable Long companyId);
}
