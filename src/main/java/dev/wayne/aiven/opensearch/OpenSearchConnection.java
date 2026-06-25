package dev.wayne.aiven.opensearch;

import java.io.IOException;

import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.transport.OpenSearchTransport;

/**
 * Owns the OpenSearch client and the transport that must be closed with it.
 */
public record OpenSearchConnection(OpenSearchClient client, OpenSearchTransport transport) implements AutoCloseable {
    @Override
    public void close() throws IOException {
        transport.close();
    }
}
