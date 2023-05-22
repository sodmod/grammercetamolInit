package com.grammercetamol;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Grammercetamol",
                version = "1.0",
                description = "Grammercetamol APIs"
        )
)
public class GrammercetamolApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrammercetamolApplication.class, args);
    }

}
