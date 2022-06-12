package com.charminseok.advertisement.message;

import com.charminseok.advertisement.openfeign.product.cache.ProductCacheService;
import com.charminseok.advertisement.openfeign.product.dto.ResponseProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;

import java.util.function.Consumer;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class ProductEventConsumer {
    private final ProductCacheService productCacheService;
    private final Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();

    @Bean
    public Consumer<?> ProductUpdate() {
        return (object) -> {
            log.info("productUpdate consume...");
            System.out.println(object.toString());
            try {
                ResponseProduct responseProduct = mapper.fromJson(object, ResponseProduct.class);
                updateProduct(responseProduct);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    private void updateProduct(ResponseProduct responseProduct) {
        ResponseProduct productCache = productCacheService.getProductCache(responseProduct.getProductId());
        if(!productCache.isEmpty()){
            productCache.updateValue(responseProduct);
            productCacheService.updateProductCache(responseProduct.getProductId(), productCache);
            log.info("[productId: {}, data: {}] is updated.", responseProduct.getProductId(), productCache.toString());
        }
    }


}
