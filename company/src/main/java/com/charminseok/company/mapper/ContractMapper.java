package com.charminseok.company.mapper;

import com.charminseok.company.domain.ContractDomain;
import com.charminseok.company.dto.ContractDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ContractMapper {
    void insertContract(
            @Param("Contract") ContractDTO contractDTO
    );

    ContractDomain selectContractByStartDate(Long companyId);

    List<ContractDomain> selectContractList();

    ContractDomain selectContractByCompanyId(Long companyId);
}
