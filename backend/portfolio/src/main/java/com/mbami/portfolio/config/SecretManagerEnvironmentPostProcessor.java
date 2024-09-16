package com.mbami.portfolio.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretVersionName;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SecretManagerEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final String PROJECT_ID = "mbamiluka-65b99"; // GCP Project ID

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        // Check if the 'prod' profile is active
        if (Arrays.asList(environment.getActiveProfiles()).contains("prod")) {
            try {
                String dbUser = accessSecretVersion(PROJECT_ID, "PORTFOLIO_MYSQL_USER");
                String dbPass = accessSecretVersion(PROJECT_ID, "PORTFOLIO_MYSQL_PASSW");
                String dbUrl = accessSecretVersion(PROJECT_ID, "PORTFOLIO_MYSQL_URL");

                System.out.println("dbUser: " + dbUser);
                System.out.println("dbPass: " + dbPass);
                System.out.println("dbUrl: " + dbUrl);

                Map<String, Object> secrets = new HashMap<>();
                secrets.put("spring.datasource.username", dbUser);
                secrets.put("spring.datasource.password", dbPass);
                secrets.put("spring.datasource.url", dbUrl);

                MapPropertySource secretPropertySource = new MapPropertySource("gcpSecrets", secrets);
                environment.getPropertySources().addFirst(secretPropertySource);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load secrets from GCP Secret Manager" + e.getMessage(), e);
            }
        }
    }

    private String accessSecretVersion(String projectId, String secretId) throws IOException {
        try (SecretManagerServiceClient client = SecretManagerServiceClient.create()) {
            SecretVersionName secretVersionName = SecretVersionName.of(projectId, secretId, "latest");
            return client.accessSecretVersion(secretVersionName).getPayload().getData().toStringUtf8();
        }
    }
}
