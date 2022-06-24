package org.dzmitry.kapachou.integration.model;

import lombok.Data;

@Data
public class MatchmakingDetails {
  private boolean ranked;
  private String mapUUID;
  private MatchmakingMode mode;
}
