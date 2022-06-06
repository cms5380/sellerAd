package com.charminseok.advertisement.feignclient.contract.service;

import com.charminseok.advertisement.feignclient.contract.dto.ResponseContract;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "contract-service")
public interface ContractService {
    @GetMapping("/contract-service/contract/{companyId}")
    ResponseContract getContractByCompanyId(@PathVariable Long companyId);
}
