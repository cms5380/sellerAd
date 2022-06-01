package com.charminseok.sellerad.company.controller;

import com.charminseok.sellerad.company.domain.CompanyDomain;
import com.charminseok.sellerad.company.dto.CompanyDTO;
import com.charminseok.sellerad.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyDomain> registerCompany(@RequestBody CompanyDTO companyDTO) throws Exception {
        companyService.registerCompany(companyDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
