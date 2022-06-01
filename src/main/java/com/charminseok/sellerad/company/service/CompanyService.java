package com.charminseok.sellerad.company.service;

import com.charminseok.sellerad.company.dto.CompanyDTO;
import com.charminseok.sellerad.company.mapper.CompanyMapper;
import com.charminseok.sellerad.product.domain.ProductDomain;
import com.charminseok.sellerad.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyMapper companyMapper;
    private final ProductService productService;
    public int registerCompany(CompanyDTO companyDTO) throws Exception {
        ProductDomain product = productService.getProductByCompanyName(companyDTO.getCompanyName());
        System.out.println(product.toString());
        if("".equals(product.getProductName())){

            throw new Exception("No such company.");
        }

        return companyMapper.insertCompany(companyDTO);
    }
}
