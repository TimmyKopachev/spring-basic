package org.dzmitry.kapachou.aop.before;


import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Data
@Component
public class DummyGameplayProcessor {

    @Autowired
    DummyGameplayProcessor dummyGameplayProcessor;

    @PostConstruct
    public void setup() {
        Player player = Player.builder()
                .nickname("player-123")
                .email("player@gmail.com")
                .build();
        dummyGameplayProcessor.start(player);
    }

    public void start(Player player) {
        log.info("Player {} has clicked to start the game", player);
    }

    @Data
    @Builder
    static class Player {
        String nickname;
        String email;
    }

}
