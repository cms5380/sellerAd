package com.charminseok.product.controller;

import com.charminseok.product.domain.ProductDomain;
import com.charminseok.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class productController {
    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<?> getProducts(@RequestParam("stock-count") int stockCount){
        List<ProductDomain> products = productService.getProductList(stockCount);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable(value = "productId") Long productId){
        ProductDomain product = productService.getProductByProductId(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<?> getProduct(@RequestParam(value = "company-name") String companyName){
        ProductDomain product = productService.getProductByCompanyName(companyName);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<?> setProduct(@RequestBody ProductDomain productDomain){
        productService.setProduct(productDomain);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
