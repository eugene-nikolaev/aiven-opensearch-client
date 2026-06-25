package dev.wayne.aiven.opensearch;

import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.transport.OpenSearchTransport;
import org.opensearch.client.transport.httpclient5.ApacheHttpClient5TransportBuilder;

public final class OpenSearchClientFactory {
    private OpenSearchClientFactory() {
    }

    /**
     * Creates a client using HTTP basic authentication and default JVM TLS verification.
     */
    public static OpenSearchConnection create(OpenSearchConfig config) {
        var host = config.httpHost();
        var credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope(host),
                new UsernamePasswordCredentials(config.username(), config.password())
        );

        var builder = ApacheHttpClient5TransportBuilder.builder(host);
        builder.setHttpClientConfigCallback(httpClientBuilder ->
                httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)
        );

        OpenSearchTransport transport = builder.build();
        return new OpenSearchConnection(new OpenSearchClient(transport), transport);
    }
}
