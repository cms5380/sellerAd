package com.charminseok.sellerad.company.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyMapper {
    void insertCompany();
    void updateCompany();
}
