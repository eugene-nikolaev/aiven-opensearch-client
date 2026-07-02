package dev.wayne.aiven.opensearch;

import java.io.IOException;
import java.util.Map;

import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch._types.HealthStatus;
import org.opensearch.client.opensearch.cluster.HealthResponse;

/**
 * Provides operations for interacting with OpenSearch.
 */
public final class OpenSearchService {
    private final OpenSearchClient client;

    public OpenSearchService(OpenSearchClient client) {
        this.client = client;
    }

    public Map<String, String> snapshot() throws IOException {
        HealthResponse health = client.cluster().health();
        return Map.of(
                "cluster_version", client.info().version().number(),
                "status", health.status().toString(),
                "activeShards", String.valueOf(health.activeShards())
        );
    }
}
