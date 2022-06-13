package com.charminseok.company.company.service.impl;

import com.charminseok.company.company.dto.CompanyInsertDto;
import com.charminseok.company.company.dto.CompanyUpdateDto;
import com.charminseok.company.company.service.CompanyService;
import com.charminseok.company.error.CompanyErrorCode;
import com.charminseok.company.error.CompanyException;
import com.charminseok.company.openfeign.client.ProductService;
import com.charminseok.company.company.domain.CompanyDomain;
import com.charminseok.company.openfeign.client.dto.ResponseProduct;
import com.charminseok.company.company.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyMapper companyMapper;
    private final ProductService productService;

    public CompanyDomain selectCompanyByCompanyName(String companyName) {
        CompanyDomain companyDomain = companyMapper.selectCompanyByCompanyName(companyName);
        if(companyDomain == null){
            throw new CompanyException(CompanyErrorCode.NO_SUCH_COMPANY);
        }

        return companyDomain;
    }

    public CompanyDomain registerCompany(CompanyInsertDto companyInsertDto) {
        ResponseProduct product = productService.getProductByCompanyName(companyInsertDto.getCompanyName());
        if(product == null){
            throw new CompanyException(CompanyErrorCode.NO_SUCH_COMPANY);
        }

        companyMapper.insertCompany(companyInsertDto);

        return CompanyDomain.builder()
                .companyName(companyInsertDto.getCompanyName())
                .companyBusinessNumber(companyInsertDto.getCompanyBusinessNumber())
                .companyTel(companyInsertDto.getCompanyTel())
                .companyAddress(companyInsertDto.getCompanyAddress())
                .companyId(companyInsertDto.getCompanyId())
                .build();
    }

    public CompanyDomain selectCompanyById(Long companyId) {
        CompanyDomain companyDomain = companyMapper.selectCompanyByCompanyId(companyId);
        if(companyDomain == null){
            throw new CompanyException(CompanyErrorCode.NO_SUCH_COMPANY);
        }

        return companyDomain;
    }

    public CompanyDomain updateCompany(Long companyId, CompanyUpdateDto companyUpdateDto) {
        CompanyDomain companyDomain = CompanyDomain.builder()
                .companyId(companyId)
                .companyAddress(companyUpdateDto.getCompanyAddress())
                .companyTel(companyUpdateDto.getCompanyTel())
                .companyBusinessNumber(companyUpdateDto.getCompanyBusinessNumber())
                .companyName(companyUpdateDto.getCompanyName())
                .build();

        companyMapper.updateCompany(companyDomain);
        return companyDomain;
    }
}
