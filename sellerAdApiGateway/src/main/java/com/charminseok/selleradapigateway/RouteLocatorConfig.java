package com.charminseok.selleradapigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteLocatorConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("company-service", route -> route.path("/company-service/**")
                        .uri("lb://COMPANY-SERVICE"))
                .route("contract-service", route -> route.path("/contract-service/**")
                        .uri("lb://CONTRACT-SERVICE"))
                .route("product", route -> route.path("/product-service/**")
                        .uri("lb://PRODUCT-SERVICE"))
                .route(route -> route.path("/advertisement-service/**")
                        .uri("lb://ADVERTISEMENT-SERVICE"))
                .build();
    }
}
