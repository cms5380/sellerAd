package com.charminseok.selleraddiscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SellerAdDiscoveryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellerAdDiscoveryServerApplication.class, args);
    }

}
