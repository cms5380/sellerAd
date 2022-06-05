package com.charminseok.company.mapper;

import com.charminseok.company.domain.CompanyDomain;
import com.charminseok.company.dto.CompanyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CompanyMapper {
    int insertCompany(
            @Param("company") CompanyDTO companyDTO
    );

    CompanyDomain selectCompanyByCompanyId(Long companyId);

    CompanyDomain selectCompanyByCompanyName(String companyName);
}
