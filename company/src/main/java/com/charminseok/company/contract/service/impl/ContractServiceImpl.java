package com.charminseok.company.contract.service.impl;


import com.charminseok.company.contract.domain.ContractDomain;
import com.charminseok.company.contract.dto.ContractInsertDto;
import com.charminseok.company.contract.dto.ContractResponseDto;
import com.charminseok.company.contract.service.ContractService;
import com.charminseok.company.error.CompanyErrorCode;
import com.charminseok.company.error.CompanyException;
import com.charminseok.company.contract.mapper.ContractMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContractServiceImpl implements ContractService {
    private final ContractMapper contractMapper;

    public ContractDomain registerContract(ContractInsertDto contractInsertDto) {
        ContractDomain contractDomain = contractMapper.selectContractByStartDate(contractInsertDto.getCompanyId());
        if (contractDomain != null) {
            throw new CompanyException(CompanyErrorCode.ALREADY_EXISTS_CONTRACT);
        }

        contractDomain = ContractDomain.builder()
                .companyId(contractInsertDto.getCompanyId())
                .contractStartDate(LocalDate.now())
                .contractEndDate(LocalDate.now().plusYears(1L)).build();

        try {
            if (contractMapper.insertContract(contractDomain) == 1) {
                return contractDomain;
            } else {
                return new ContractDomain();
            }
        } catch (Exception exception) {
            throw new CompanyException(CompanyErrorCode.NO_SUCH_COMPANY);
        }
    }

    public List<ContractDomain> getContractList() {
        return contractMapper.selectContractList();
    }

    public ContractResponseDto getContractByCompanyId(Long companyId) {
        ContractResponseDto contractResponseDto = contractMapper.selectContractByCompanyId(companyId);
        if (contractResponseDto == null) {
            throw new CompanyException(CompanyErrorCode.NOT_EXISTS_COMPANY_OR_CONTRACT);
        }

        if (contractResponseDto.getContractEndDate().isBefore(LocalDate.now())) {
            throw new CompanyException(CompanyErrorCode.EXPIRED_CONTRACT);
        }

        return contractResponseDto;
    }
}
