package dev.wayne.aiven.opensearch;

public final class Main {
    private Main() {}

    public static void main(String[] args) {
        try {
            OpenSearchConfig config = OpenSearchConfig.fromEnvironment();

            try (OpenSearchConnection connection = OpenSearchClientFactory.create(config)) {
                var info = connection.client().info();
                System.out.println(info.version().number());
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
