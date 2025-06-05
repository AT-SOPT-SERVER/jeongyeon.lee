package org.sopt.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class SwaggerConfig {
    private final String securityScheme = "Bearer";
    private final String bearerFormat = "JWT";
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(setComponents())
                .addSecurityItem(setSecurityItems());
    }
    private Components setComponents() {
        return new Components()
                .addSecuritySchemes(securityScheme, bearerAuth());
    }

    private SecurityScheme bearerAuth() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme(securityScheme)
                .bearerFormat(bearerFormat)
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION);
    }

    private SecurityRequirement setSecurityItems() {
        return new SecurityRequirement()
                .addList(securityScheme);
    }
}
