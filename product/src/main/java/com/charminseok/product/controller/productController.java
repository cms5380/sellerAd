package com.charminseok.product.controller;

import com.charminseok.product.domain.ProductDomain;
import com.charminseok.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class productController {
    private final ProductService productService;

    @GetMapping("/product/{companyName}")
    public ResponseEntity<?> getProducts(@PathVariable("companyName") String companyName){
        ProductDomain product = productService.getProductByCompanyName(companyName);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
