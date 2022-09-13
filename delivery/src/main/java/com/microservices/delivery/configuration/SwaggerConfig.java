package com.microservices.delivery.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI deliveryOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Delivery API")
                        .description("Spring delivery sample application")
                        .version("v0.0.1")
                );
    }
}
