package com.charminseok.company.service;

import com.charminseok.company.dto.CompanyInsertDto;
import com.charminseok.company.dto.CompanyUpdateDto;
import com.charminseok.company.openfeign.client.ProductService;
import com.charminseok.company.domain.CompanyDomain;
import com.charminseok.company.dto.ResponseProduct;
import com.charminseok.company.mapper.CompanyMapper;
import com.charminseok.company.openfeign.client.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyMapper companyMapper;
    private final ProductService productService;
    private final StreamBridge streamBridge;

    public CompanyDomain selectCompanyByCompanyId(Long companyId) {
        return companyMapper.selectCompanyByCompanyId(companyId);
    }

    public CompanyDomain selectCompanyByCompanyName(String companyName) {
        return companyMapper.selectCompanyByCompanyName(companyName);
    }

    public CompanyDomain registerCompany(CompanyInsertDto companyInsertDto) throws Exception {
        ResponseProduct product = productService.getProductByCompanyName(new ProductDto(companyInsertDto.getCompanyName()));
        if(product == null){
            throw new Exception("no such company.");
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
        return companyMapper.selectCompanyByCompanyId(companyId);
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
