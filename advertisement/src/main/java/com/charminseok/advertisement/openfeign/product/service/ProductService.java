package com.charminseok.advertisement.openfeign.product.service;

import com.charminseok.advertisement.openfeign.product.dto.ProductDto;
import com.charminseok.advertisement.openfeign.product.dto.ResponseProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "product")
public interface ProductService {

    @GetMapping("/products")
    List<ResponseProduct> getProductList(@RequestParam("stock-count") int stockCount, @RequestParam("start") int start, @RequestParam("page-size") int pageSize);

    @GetMapping("/product/{productId}")
    ResponseProduct getProductById(@PathVariable(value = "productId") Long productId, @SpringQueryMap ProductDto productDto);
}
