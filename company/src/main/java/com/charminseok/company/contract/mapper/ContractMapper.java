package com.charminseok.company.contract.mapper;

import com.charminseok.company.contract.domain.ContractDomain;
import com.charminseok.company.contract.dto.ContractResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractMapper {
    int insertContract(ContractDomain contractDomain);

    ContractDomain selectContractByStartDate(Long companyId);

    List<ContractDomain> selectContractList();

    ContractResponseDto selectContractByCompanyId(Long companyId);
}
