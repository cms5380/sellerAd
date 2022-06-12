package com.charminseok.selleradapigateway;

import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public CommandLineRunner openApiGroups(RouteDefinitionLocator locator, SwaggerUiConfigParameters swaggerUiParameters) {
        return args -> locator
                .getRouteDefinitions().collectList().block()
                .stream()
                .filter(routeDefinition -> !"seller-ad-gateway".equals(routeDefinition.getId()))
                .map(RouteDefinition::getId)
                .forEach(swaggerUiParameters::addGroup);
    }
}
