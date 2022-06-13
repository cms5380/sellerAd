package com.charminseok.product.controller;

import com.charminseok.product.domain.ProductDomain;
import com.charminseok.product.dto.ProductCreateDto;
import com.charminseok.product.dto.ProductUpdateDto;
import com.charminseok.product.dto.Paging;
import com.charminseok.product.dto.RequestProduct;
import com.charminseok.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<?> getProducts(@ModelAttribute RequestProduct requestProduct, @ModelAttribute Paging paging){
        List<ProductDomain> products = productService.getProductList(requestProduct, paging);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable(value = "productId") Long productId, @ModelAttribute RequestProduct requestProduct){
        ProductDomain product = productService.getProduct(productId, requestProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<?> getProduct(@RequestParam String companyName){
        ProductDomain product = productService.getProductByCompanyName(companyName);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @PostMapping("/product")
    public ResponseEntity<?> setProduct(@RequestBody ProductCreateDto productCreateDto){
        ProductDomain item = productService.insertProduct(productCreateDto);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductUpdateDto productUpdateDto){
        ProductDomain item = productService.updateProduct(productId, productUpdateDto);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
