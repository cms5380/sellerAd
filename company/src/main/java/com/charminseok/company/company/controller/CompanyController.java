package com.charminseok.company.company.controller;

import com.charminseok.company.company.domain.CompanyDomain;
import com.charminseok.company.company.dto.CompanyInsertDto;
import com.charminseok.company.company.dto.CompanyUpdateDto;
import com.charminseok.company.company.service.impl.CompanyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RequiredArgsConstructor
@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyServiceImpl companyService;

    @PostMapping
    public ResponseEntity<CompanyDomain> registerCompany(@Valid @RequestBody CompanyInsertDto companyInsertDto) throws Exception {
        return new ResponseEntity<>(companyService.registerCompany(companyInsertDto), HttpStatus.OK);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyDomain> getCompanyById(@PathVariable(value = "companyId") Long companyId) {
        CompanyDomain companyDomain = companyService.selectCompanyById(companyId);

        return new ResponseEntity<>(companyDomain, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CompanyDomain> getCompanyByNAme(@RequestParam(value = "companyName", required = false) String companyName) {
        CompanyDomain companyDomain = companyService.selectCompanyByCompanyName(companyName);

        return new ResponseEntity<>(companyDomain, HttpStatus.OK);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<?> updateCompany(
            @PathVariable(value = "companyId") @Min(1000000001L) @Max(9999999999L) Long companyId,
            @RequestBody CompanyUpdateDto companyUpdateDto
    ){
        CompanyDomain companyDomain = companyService.updateCompany(companyId, companyUpdateDto);

        return new ResponseEntity<>(companyDomain, HttpStatus.OK);
    }

}
