package dev.wayne.aiven.opensearch.monitoring;

import java.util.Map;

public interface UpstreamSender {
    void sendMetrics(Map<String, String> metricsMap) throws UpstreamSendException;
}
