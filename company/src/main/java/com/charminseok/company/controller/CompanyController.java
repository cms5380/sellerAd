package com.charminseok.company.controller;

import com.charminseok.company.domain.CompanyDomain;
import com.charminseok.company.dto.CompanyDTO;
import com.charminseok.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyDomain> registerCompany(@RequestBody CompanyDTO companyDTO) throws Exception {
        companyService.registerCompany(companyDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CompanyDomain> getCompany(
            @RequestParam(value = "companyName", required = false) String companyName,
            @RequestParam(value = "companyId", required = false) Long companyId
    ) {
        CompanyDomain companyDomain = null;
        if (companyName == null && companyId != null) {
            companyDomain = companyService.selectCompanyById(companyId);
        } else if (companyName != null && companyId == null) {
            companyDomain = companyService.selectCompanyByCompanyName(companyName);
        }

        return new ResponseEntity<>(companyDomain, HttpStatus.OK);
    }

}
