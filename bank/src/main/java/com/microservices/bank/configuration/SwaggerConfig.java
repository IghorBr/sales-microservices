package com.microservices.bank.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI bankOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Bank API")
                        .description("Spring bank sample application")
                        .version("v0.0.1")
                );
    }
}
