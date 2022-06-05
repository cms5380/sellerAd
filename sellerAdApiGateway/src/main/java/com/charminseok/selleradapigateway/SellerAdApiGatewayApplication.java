package com.charminseok.selleradapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SellerAdApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellerAdApiGatewayApplication.class, args);
    }

}
