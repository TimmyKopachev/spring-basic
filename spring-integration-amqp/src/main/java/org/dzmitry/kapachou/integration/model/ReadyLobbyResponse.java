package org.dzmitry.kapachou.integration.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReadyLobbyResponse {

    private String lobbyUUID;

    private String playerUUID;
    private SearchingMatchmakingRequest.MatchmakingDetails matchmakingDetails;

}
