package com.pragma.payments.infrastructure.config;


import com.pragma.payments.domain.model.Payment;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${spring.application.name:payments}")
    private String applicationName;

    @Value("${app.version:1.0.0}")
    private String apiVersion;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Payments API")
                        .version(apiVersion)
                        .description("""
                                Sistema de gestión de pagos con integración de servicios externos.
                                
                                ## Funcionalidades
                                - Creación y gestión de pagos
                                - Evaluación de fraude mediante motor externo
                                - Evaluación de riesgo mediante buró de riesgos
                                - Procesamiento de pagos a través de gateway
                                
                                ## Servicios Externos
                                - **Fraud Engine**: Evaluación de fraude en tiempo real
                                - **Risk Bureau**: Análisis de riesgo crediticio
                                - **Payment Gateway**: Procesamiento de transacciones
                                """)
                        .contact(new Contact()
                                .name("Pragma Team")
                                .email("team@pragma.co")
                                .url("https://www.pragma.co"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("JWT token de autenticación")));
    }

    @Bean
    public io.swagger.v3.oas.models.servers.Server devServer() {
        io.swagger.v3.oas.models.servers.Server server = new io.swagger.v3.oas.models.servers.Server();
        server.setUrl("{scheme}://{host}:{port}/{basePath}");
        server.setDescription("Servidor de desarrollo");
        server.addVariablesItem(new io.swagger.v3.oas.models.servers.ServerVariable()
                .addEnumItem("scheme")
                .addEnumItem("http")
                .addEnumItem("https"));
        return server;
    }
}