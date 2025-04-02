package com.mbami.portfolio.security.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Component
public class CorsPropertiesLoader {
    public String[] loadAllowedOrigins() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new ClassPathResource("allowed_ips.txt").getInputStream(), StandardCharsets.UTF_8))) {
            String ips = reader.lines().collect(Collectors.joining(","));
            // Prepend a protocol to each IP (if necessary), e.g., "http://"
            // In this example we assume they are already complete or can be handled as-is.
            return ips.split(",");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new String[0];
        }
    }
}
