package org.dzmitry.kapachou.integration.aggregation;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.dzmitry.kapachou.integration.config.CustomDeserializer;
import org.dzmitry.kapachou.integration.model.SearchingMatchmakingRequest;
import org.springframework.integration.aggregator.ReleaseStrategy;
import org.springframework.integration.store.MessageGroup;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ReleaseLobbyAggregationStrategy implements ReleaseStrategy {

    final CustomDeserializer<SearchingMatchmakingRequest> deserializer;

    @SneakyThrows
    @Override
    public boolean canRelease(MessageGroup messageGroup) {
        var request = deserializer.deserialize((String) messageGroup.getOne().getPayload());
        return Optional.of(request.getMatchmakingDetails())
                .map(md -> md.getMode().getPlayerSize() == messageGroup.getMessages().size())
                .orElse(Boolean.FALSE);
    }
}
