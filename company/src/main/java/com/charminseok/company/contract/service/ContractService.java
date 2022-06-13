package com.charminseok.company.contract.service;

import com.charminseok.company.contract.domain.ContractDomain;
import com.charminseok.company.contract.dto.ContractInsertDto;
import com.charminseok.company.contract.dto.ContractResponseDto;
import java.util.List;

public interface ContractService {
    ContractDomain registerContract(ContractInsertDto contractInsertDto);

    List<ContractDomain> getContractList();

    ContractResponseDto getContractByCompanyId(Long companyId);
}
