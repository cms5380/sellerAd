package com.charminseok.company.mapper;

import com.charminseok.company.domain.ContractDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractMapper {
    int insertContract(ContractDomain contractDomain);

    ContractDomain selectContractByStartDate(Long companyId);

    List<ContractDomain> selectContractList();

    ContractDomain selectContractByCompanyId(Long companyId);
}
