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
@RequestMapping("/company-service")
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("/company")
    public ResponseEntity<CompanyDomain> registerCompany(@RequestBody CompanyDTO companyDTO) throws Exception {
        companyService.registerCompany(companyDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/company/{companyName}")
    public ResponseEntity<CompanyDomain> getCompanyByCompanyName(@PathVariable("companyName") String companyName){
        CompanyDomain companyDomain = companyService.selectCompanyByCompanyName(companyName);
        return new ResponseEntity<>(companyDomain, HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
