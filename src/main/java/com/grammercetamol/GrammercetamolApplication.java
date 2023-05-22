package com.grammercetamol;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class GrammercetamolApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrammercetamolApplication.class, args);
    }

}
