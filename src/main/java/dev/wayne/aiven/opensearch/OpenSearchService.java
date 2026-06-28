package dev.wayne.aiven.opensearch;

import java.io.IOException;

import org.opensearch.client.opensearch.OpenSearchClient;

/**
 * Provides operations for interacting with OpenSearch.
 */
public final class OpenSearchService {
    private final OpenSearchClient client;

    public OpenSearchService(OpenSearchClient client) {
        this.client = client;
    }

    /**
     * Reads the version reported by the OpenSearch cluster.
     *
     * @return the cluster version number
     * @throws IOException if the request to OpenSearch fails
     */
    public String clusterVersion() throws IOException {
        return client.info().version().number();
    }
}
