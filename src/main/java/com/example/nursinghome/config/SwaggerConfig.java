package com.example.nursinghome.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Main service nursing", description = "Inventory API documentation", version = "1.0")
)
public class SwaggerConfig {
}
