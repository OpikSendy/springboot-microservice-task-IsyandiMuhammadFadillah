package com.isyandi.book.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Simple Book Management Microservice API")
                        .version("1.0.0")
                        .description("RESTful microservice for Book Management built with Java 17, Spring Boot 3.3.5, PostgreSQL, and Flyway Migration.")
                        .contact(new Contact()
                                .name("Isyandi Muhammad Fadillah")
                                .email("opiksendy@gmail.com")
                                .url("https://github.com/OpikSendy"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
