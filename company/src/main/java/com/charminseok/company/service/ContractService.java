package com.charminseok.company.service;


import com.charminseok.company.domain.CompanyDomain;
import com.charminseok.company.domain.ContractDomain;
import com.charminseok.company.dto.ContractDTO;
import com.charminseok.company.dto.RequestContract;
import com.charminseok.company.mapper.ContractMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContractService {
    private final ContractMapper contractMapper;

    public void registerContract(RequestContract requestContract) {
        ContractDomain contractDomain = contractMapper.selectContractByStartDate(requestContract.getCompanyId());

        if(contractDomain != null){
            throw new RuntimeException("해당 날짜에 이미 계약이 있습니다.");
        }

        ContractDTO contractDTO = ContractDTO.builder()
                .companyId(requestContract.getCompanyId())
                .contractStartDate(LocalDate.now())
                .contractEndDate(LocalDate.now().plusYears(1L)).build();

        contractMapper.insertContract(contractDTO);
    }

    public List<ContractDomain> getContractList() {
        return contractMapper.selectContractList();
    }

    public ContractDomain getContractByCompanyId(Long companyId) {
        return contractMapper.selectContractByCompanyId(companyId);
    }
}
