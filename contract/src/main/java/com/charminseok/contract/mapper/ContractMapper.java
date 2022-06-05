package com.charminseok.contract.mapper;

import com.charminseok.contract.domain.ContractDomain;
import com.charminseok.contract.dto.ContractDTO;
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
}
