package dev.wayne.aiven.opensearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.Test;

class OpenSearchConfigTest {
    @Test
    void readsRequiredEnvironmentValues() {
        OpenSearchConfig config = OpenSearchConfig.from(Map.of(
                "OPENSEARCH_URL", "https://example.com:12345",
                "OPENSEARCH_USERNAME", "avnadmin",
                "OPENSEARCH_PASSWORD", "secret"
        ));

        assertEquals("https://example.com:12345", config.url().toString());
        assertEquals("avnadmin", config.username());
        assertArrayEquals("secret".toCharArray(), config.password());
    }

    @Test
    void convertsUrlToHttpHost() {
        OpenSearchConfig config = new OpenSearchConfig(
                java.net.URI.create("https://example.com:12345"),
                "avnadmin",
                "secret".toCharArray()
        );

        var host = config.httpHost();

        assertEquals("https", host.getSchemeName());
        assertEquals("example.com", host.getHostName());
        assertEquals(12345, host.getPort());
    }

    @Test
    void defaultsHttpsPortWhenUrlDoesNotSpecifyOne() {
        OpenSearchConfig config = new OpenSearchConfig(
                java.net.URI.create("https://example.com"),
                "avnadmin",
                "secret".toCharArray()
        );

        assertEquals(443, config.httpHost().getPort());
    }

    @Test
    void rejectsMissingEnvironmentValue() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> OpenSearchConfig.from(Map.of(
                        "OPENSEARCH_URL", "https://example.com:12345",
                        "OPENSEARCH_USERNAME", "avnadmin"
                ))
        );

        assertEquals("Missing required environment variable: OPENSEARCH_PASSWORD", exception.getMessage());
    }

    @Test
    void rejectsNonHttpsUrl() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new OpenSearchConfig(
                        java.net.URI.create("http://example.com:9200"),
                        "avnadmin",
                        "secret".toCharArray()
                )
        );

        assertEquals("OPENSEARCH_URL must use the https scheme", exception.getMessage());
    }
}
