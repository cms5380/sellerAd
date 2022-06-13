package com.charminseok.company.company.service;

import com.charminseok.company.company.domain.CompanyDomain;
import com.charminseok.company.company.dto.CompanyInsertDto;
import com.charminseok.company.company.dto.CompanyUpdateDto;

public interface CompanyService {

    CompanyDomain selectCompanyByCompanyName(String companyName);

    CompanyDomain registerCompany(CompanyInsertDto companyInsertDto);

    CompanyDomain selectCompanyById(Long companyId);

    CompanyDomain updateCompany(Long companyId, CompanyUpdateDto companyUpdateDto);
}
