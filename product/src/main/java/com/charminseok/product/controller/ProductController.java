package com.charminseok.product.controller;

import com.charminseok.product.domain.ProductDomain;
import com.charminseok.product.dto.RequestPaging;
import com.charminseok.product.dto.RequestProduct;
import com.charminseok.product.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productServiceImpl;

    @GetMapping("/products")
    public ResponseEntity<?> getProducts(@ModelAttribute RequestProduct requestProduct, @ModelAttribute RequestPaging requestPaging){
        List<ProductDomain> products = productServiceImpl.getProductList(requestProduct, requestPaging);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable(value = "productId") Long productId, @ModelAttribute RequestProduct requestProduct){
        requestProduct.setProductId(productId);
        ProductDomain product = productServiceImpl.getProductByProductId(requestProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<?> getProduct(@ModelAttribute RequestProduct requestProduct){
        ProductDomain product = productServiceImpl.getProduct(requestProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<?> setProduct(@RequestBody ProductDomain productDomain){
        productServiceImpl.setProduct(productDomain);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
