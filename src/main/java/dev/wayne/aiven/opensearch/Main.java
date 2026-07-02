package dev.wayne.aiven.opensearch;

import dev.wayne.aiven.opensearch.monitoring.UpstreamSender;
import dev.wayne.aiven.opensearch.monitoring.UpstreamSenderFactory;

public final class Main {
    private Main() {}

    public static void main(String[] args) {
        try {
            OpenSearchConfig config = OpenSearchConfig.fromEnvironment();

            try (OpenSearchConnection connection = OpenSearchClientFactory.create(config)) {
                var service = new OpenSearchService(connection.client());
                //System.out.println(service.clusterVersion());
                UpstreamSender sender = UpstreamSenderFactory.sender();
                sender.sendMetrics(service.snapshot());
            }
        } catch (IllegalArgumentException exception) {
            System.err.println("Configuration error: " + exception.getMessage());
            System.exit(2);
        } catch (Exception exception) {
            System.err.println("Failed to connect to OpenSearch: " + exception.getMessage());
            System.exit(1);
        }
    }
}
