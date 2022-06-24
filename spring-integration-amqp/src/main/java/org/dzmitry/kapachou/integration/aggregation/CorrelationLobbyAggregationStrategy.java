package org.dzmitry.kapachou.integration.aggregation;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.dzmitry.kapachou.integration.config.CustomDeserializer;
import org.dzmitry.kapachou.integration.model.SearchingMatchmakingRequest;
import org.springframework.integration.aggregator.CorrelationStrategy;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CorrelationLobbyAggregationStrategy implements CorrelationStrategy {


    final CustomDeserializer<SearchingMatchmakingRequest> deserializer;

    @SneakyThrows
    @Override
    public Object getCorrelationKey(Message<?> message) {
        var request = deserializer.deserialize((String) message.getPayload());
        return request.getMatchmakingDetails().hashCode();

    }


}
