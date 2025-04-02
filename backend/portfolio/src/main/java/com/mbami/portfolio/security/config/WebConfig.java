package com.mbami.portfolio.security.config;

import com.mbami.portfolio.security.config.CorsPropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final CorsPropertiesLoader corsPropertiesLoader;

    @Autowired
    public WebConfig(CorsPropertiesLoader corsPropertiesLoader) {
        this.corsPropertiesLoader = corsPropertiesLoader;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // You could combine with other origins defined in application.properties if needed
        registry.addMapping("/**")
            .allowedOrigins(corsPropertiesLoader.loadAllowedOrigins())
            .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
            .allowedHeaders("*")
            .allowCredentials(true);
    }
}
