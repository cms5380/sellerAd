package com.charminseok.sellerad.company.mapper;

import com.charminseok.sellerad.company.domain.CompanyDomain;
import com.charminseok.sellerad.company.dto.CompanyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CompanyMapper {
    int insertCompany(
            @Param("company") CompanyDTO companyDTO
    );
}
