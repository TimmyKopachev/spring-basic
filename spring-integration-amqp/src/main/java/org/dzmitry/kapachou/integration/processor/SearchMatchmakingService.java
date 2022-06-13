package org.dzmitry.kapachou.integration.processor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dzmitry.kapachou.integration.model.ReadyLobbyResponse;
import org.dzmitry.kapachou.integration.model.SearchingMatchmakingRequest;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class SearchMatchmakingService {

    @ServiceActivator(inputChannel = "search.matchmaking.channel")
    void findMatchmaking(Message<SearchingMatchmakingRequest> requestMessage) {
        MessageChannel replyChannel = (MessageChannel) requestMessage.getHeaders().getReplyChannel();
        SearchingMatchmakingRequest request = MessageBuilder.fromMessage(requestMessage).build().getPayload();

        log.info("find a matchmaking by request: {}", request);

        replyChannel.send(generateLobbyResponseByRequest(request));
    }

    private static Message<ReadyLobbyResponse> generateLobbyResponseByRequest(SearchingMatchmakingRequest request) {
        ReadyLobbyResponse readyLobbyResponse = ReadyLobbyResponse.builder()
                .lobbyUUID(UUID.randomUUID().toString())
                .playerUUID(request.getPlayerUUID())
                .matchmakingDetails(request.getMatchmakingDetails())
                .build();


        return MessageBuilder.withPayload(readyLobbyResponse).build();
    }


}
