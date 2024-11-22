package com.oak.beardbuddy.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permitir todas as rotas
                .allowedOrigins("http://localhost:4200") // Permitir origem específica
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permitir métodos
                .allowedHeaders("*")
                .allowCredentials(true); // Permitir todos os cabeçalhos
    }
}
