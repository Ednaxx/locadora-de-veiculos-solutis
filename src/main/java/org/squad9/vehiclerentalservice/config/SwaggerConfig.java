package org.squad9.vehiclerentalservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(new Info().title("Locadora de Veículos - Solutis Cars").description("Documentação da API Locadora de Veículos, desenvolvida pela equipe 9 para o desafio Solutis").version("v1.0.0").license(new License().name("MIT").url("https://github.com/Ednaxx/locadora-de-veiculos-solutis/blob/development/LICENSE")));
    }
}