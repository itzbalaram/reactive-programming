package com.itzbalaram.reactive.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Spring WebFlux Crud Example",
        version = "1.0",
        description = "Sample Document"
))
public class ReactiveProgrammingApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReactiveProgrammingApplication.class, args);
        System.out.println("Welcome to the Reactive Programming System");
    }
}