package org.dzmitry.kapachou.integration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MatchmakingMode {
  DEATHMATCH(3),
  CAPTURE_FLAG(3);

  private final int playerSize;
}
