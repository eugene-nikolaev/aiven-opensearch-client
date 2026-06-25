package dev.wayne.aiven.opensearch;

import java.net.URI;
import java.util.Map;

import org.apache.hc.core5.http.HttpHost;

public record OpenSearchConfig(URI url, String username, char[] password) {
    private static final String URL_ENV = "OPENSEARCH_URL";
    private static final String USERNAME_ENV = "OPENSEARCH_USERNAME";
    private static final String PASSWORD_ENV = "OPENSEARCH_PASSWORD";

    public OpenSearchConfig {
        if (!"https".equalsIgnoreCase(url.getScheme())) {
            throw new IllegalArgumentException(URL_ENV + " must use the https scheme");
        }
        if (url.getHost() == null || url.getHost().isBlank()) {
            throw new IllegalArgumentException(URL_ENV + " must include a host");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException(USERNAME_ENV + " must not be blank");
        }
        if (password == null || password.length == 0) {
            throw new IllegalArgumentException(PASSWORD_ENV + " must not be blank");
        }
    }

    public static OpenSearchConfig fromEnvironment() {
        Map<String, String> environment = System.getenv();
        return new OpenSearchConfig(
                URI.create(required(environment, URL_ENV)),
                required(environment, USERNAME_ENV),
                required(environment, PASSWORD_ENV).toCharArray()
        );
    }

    public HttpHost httpHost() {
        return new HttpHost(url.getScheme(), url.getHost(), port());
    }

    private int port() {
        if (url.getPort() != -1) {
            return url.getPort();
        }
        return "https".equalsIgnoreCase(url.getScheme()) ? 443 : 80;
    }

    private static String required(Map<String, String> environment, String name) {
        String value = environment.get(name);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing required environment variable: " + name);
        }
        return value;
    }
}
