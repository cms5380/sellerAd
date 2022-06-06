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
                .route("company", route -> route.path("/company/**")
                        .uri("lb://COMPANY"))
                .route("company", route -> route.path("/contract/**")
                        .uri("lb://COMPANY"))
                .route("product", route -> route.path("/product/**")
                        .uri("lb://PRODUCT"))
                .route(route -> route.path("/advertisement/**")
                        .uri("lb://ADVERTISEMENT"))
                .build();
    }
}
