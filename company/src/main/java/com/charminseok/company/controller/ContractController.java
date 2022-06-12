package com.charminseok.company.controller;

import com.charminseok.company.domain.ContractDomain;
import com.charminseok.company.dto.ContractInsertDto;
import com.charminseok.company.service.ContractService;
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
        ContractDomain contractByCompanyId = contractService.getContractByCompanyId(companyId);
        return new ResponseEntity<>(contractByCompanyId, HttpStatus.OK);
    }
}