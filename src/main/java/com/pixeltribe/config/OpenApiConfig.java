package com.pixeltribe.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // 定義 JWT 的 Security Scheme
        final String securitySchemeName = "bearerAuth"; // 這是一個自訂的名稱，後面會用到

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP) // SecurityScheme 類型為 HTTP
                                                .scheme("bearer") // Scheme 為 bearer
                                                .bearerFormat("JWT") // Bearer 格式為 JWT
                                )
                )
                .info(new Info().title("ForumSys API").version("v1.0.0"));
    }
}