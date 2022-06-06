package com.charminseok.company.service;

import com.charminseok.company.openfeign.client.ProductService;
import com.charminseok.company.domain.CompanyDomain;
import com.charminseok.company.dto.CompanyDTO;
import com.charminseok.company.dto.ResponseProduct;
import com.charminseok.company.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyMapper companyMapper;
    private final ProductService productService;

    public CompanyDomain selectCompanyByCompanyId(Long companyId) {
        return companyMapper.selectCompanyByCompanyId(companyId);
    }

    public CompanyDomain selectCompanyByCompanyName(String companyName) {
        return companyMapper.selectCompanyByCompanyName(companyName);
    }

    public int registerCompany(CompanyDTO companyDTO) throws Exception {
        ResponseProduct product = productService.getProductByCompanyName(companyDTO.getCompanyName());
        if(product == null){
            throw new Exception("no such company.");
        }

        return companyMapper.insertCompany(companyDTO);
    }

    public CompanyDomain selectCompanyById(Long companyId) {
        return companyMapper.selectCompanyByCompanyId(companyId);
    }
}
