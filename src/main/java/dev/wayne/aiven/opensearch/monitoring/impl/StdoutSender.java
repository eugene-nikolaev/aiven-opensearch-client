package dev.wayne.aiven.opensearch.monitoring.impl;

import dev.wayne.aiven.opensearch.monitoring.UpstreamSendException;
import dev.wayne.aiven.opensearch.monitoring.UpstreamSender;

import java.util.Map;

public class StdoutSender implements UpstreamSender {

    @Override
    public void sendMetrics(Map<String, String> metricsMap) throws UpstreamSendException {
        System.out.println(metricsMap);
    }
}
    //    @Override
//    public void sendMetrics(String metricsName, String payload) {
//        System.out.printf("metric: %s, value: %s%n", metricsName, payload);
//   }
