package com.microservices.sales.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI salesOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Sales API")
                        .description("Spring sales sample application")
                        .version("v0.0.1")
                );
    }
}
