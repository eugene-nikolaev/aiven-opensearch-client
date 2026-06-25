package dev.wayne.aiven.opensearch;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) throws Exception {
        OpenSearchConfig config = OpenSearchConfig.fromEnvironment();

        try (OpenSearchConnection connection = OpenSearchClientFactory.create(config)) {
            var info = connection.client().info();
            System.out.println(info.version().number());
        }
    }
}
