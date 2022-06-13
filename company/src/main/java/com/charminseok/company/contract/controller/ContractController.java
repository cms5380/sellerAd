package com.charminseok.company.contract.controller;

import com.charminseok.company.contract.domain.ContractDomain;
import com.charminseok.company.contract.dto.ContractInsertDto;
import com.charminseok.company.contract.dto.ContractResponseDto;
import com.charminseok.company.contract.service.ContractService;
import com.charminseok.company.contract.service.impl.ContractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contract")
public class ContractController {
    private final ContractService contractService;

    @PostMapping
    public ResponseEntity<?> registerCompany(@RequestBody ContractInsertDto contractInsertDto) {
        ContractDomain item = contractService.registerContract(contractInsertDto);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getContractList() {
        List<ContractDomain> contractList = contractService.getContractList();

        return new ResponseEntity<>(contractList, HttpStatus.OK);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<?> getContractByCompanyId(@PathVariable("companyId") Long companyId) {
        ContractResponseDto item = contractService.getContractByCompanyId(companyId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}