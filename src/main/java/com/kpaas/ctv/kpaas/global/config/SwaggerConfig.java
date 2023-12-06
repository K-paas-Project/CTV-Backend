package com.kpaas.ctv.kpaas.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        String jwtSchemeName = "BearerToken";
        SecurityRequirement securityRequirement =
                new SecurityRequirement().addList(jwtSchemeName);
        Components components = new Components().addSecuritySchemes(jwtSchemeName,
                new SecurityScheme()
                        .name(jwtSchemeName)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .in(SecurityScheme.In.HEADER)
                        .type(SecurityScheme.Type.HTTP));

        return new OpenAPI()
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}