package org.dzmitry.kapachou.integration.processor;

import org.dzmitry.kapachou.integration.model.ReadyLobbyResponse;
import org.dzmitry.kapachou.integration.model.SearchingMatchmakingRequest;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface SearchMatchmakingGateway {

    @Gateway(requestChannel = "search.matchmaking.channel")
    ReadyLobbyResponse searchMatchmaking(SearchingMatchmakingRequest request);
}
