package com.mbami.portfolio.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("https://mbamiluka-65b99.web.app/", "https://mbamiluka-65b99.firebaseapp.com/")
            .allowedOrigins("http://localhost:3000")
            .allowedOrigins("https://mbamiluka0-1-0.onrender.com")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
            .allowedHeaders("*")
            .allowCredentials(true);
    }
    
}
