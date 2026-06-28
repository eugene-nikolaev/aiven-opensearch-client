package dev.wayne.aiven.opensearch;

public final class Main {
    private Main() {}

    public static void main(String[] args) {
        try {
            OpenSearchConfig config = OpenSearchConfig.fromEnvironment();

            try (OpenSearchConnection connection = OpenSearchClientFactory.create(config)) {
                var service = new OpenSearchService(connection.client());
                System.out.println(service.clusterVersion());
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
