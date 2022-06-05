package com.charminseok.contract.service;

import com.charminseok.contract.domain.ContractDomain;
import com.charminseok.contract.dto.RequestContract;
import com.charminseok.contract.openfeign.client.CompanyService;
import com.charminseok.contract.dto.ResponseCompany;
import com.charminseok.contract.dto.ContractDTO;
import com.charminseok.contract.mapper.ContractMapper;
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
    private final CompanyService companyService;

    public void registerContract(RequestContract requestContract) {
        ResponseCompany company = companyService.getCompanyByCompanyName(requestContract.getCompanyName());

        if (company.getCompanyId() > 1000000000) {
            ContractDomain contractDomain = contractMapper.selectContractByStartDate(company.getCompanyId());

            if(contractDomain != null){
                throw new RuntimeException("해당 날짜에 이미 계약이 있습니다.");
            }

            ContractDTO contractDTO = ContractDTO.builder()
                    .companyId(company.getCompanyId())
                    .contractStartDate(LocalDate.now())
                    .contractEndDate(LocalDate.now().plusYears(1L)).build();


            contractMapper.insertContract(contractDTO);
        }


    }

    public List<ContractDomain> getContractList() {
        return contractMapper.selectContractList();
    }
}
