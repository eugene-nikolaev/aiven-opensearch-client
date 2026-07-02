package dev.wayne.aiven.opensearch.monitoring;

import dev.wayne.aiven.opensearch.monitoring.impl.StdoutSender;

public class UpstreamSenderFactory {
    public static UpstreamSender sender() {
        return new StdoutSender();
    }
}
