package com.charminseok.company.mapper;

import com.charminseok.company.domain.CompanyDomain;
import com.charminseok.company.dto.CompanyInsertDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyMapper {
    int insertCompany(
            CompanyInsertDto companyInsertDto
    );

    CompanyDomain selectCompanyByCompanyId(Long companyId);

    CompanyDomain selectCompanyByCompanyName(String companyName);

    void updateCompany(CompanyDomain companyDomain);
}
