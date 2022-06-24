package org.dzmitry.kapachou.integration.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReadyLobbyResponse {

  private String lobbyUUID;

  private List<String> playerUUIDs;
  private MatchmakingDetails matchmakingDetails;
}
