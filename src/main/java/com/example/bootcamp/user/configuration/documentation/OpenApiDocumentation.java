package com.example.bootcamp.user.configuration.documentation;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiDocumentation {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Servicio de usuarios")
                        .version("1.0")
                        .description("Microservicio para la gestión de usuarios y todo lo relacionado con ellos"))
                .addSecurityItem(new SecurityRequirement().addList("JWT"));
    }
}
