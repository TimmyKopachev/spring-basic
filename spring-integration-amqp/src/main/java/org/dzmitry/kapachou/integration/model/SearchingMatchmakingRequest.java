package org.dzmitry.kapachou.integration.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SearchingMatchmakingRequest {

    private String playerUUID;
    private MatchmakingDetails matchmakingDetails;

    @Data
    public static class MatchmakingDetails {
        private boolean ranked;
        private String mapUUID;
        private MatchmakingMode mode;
    }

    @AllArgsConstructor
    public enum MatchmakingMode {
        DEATHMATCH(16), CAPTURE_FLAG(12);
        private final int playerSize;

    }
}
