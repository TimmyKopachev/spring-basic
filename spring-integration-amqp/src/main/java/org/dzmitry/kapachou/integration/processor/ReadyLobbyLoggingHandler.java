package org.dzmitry.kapachou.integration.processor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dzmitry.kapachou.integration.model.ReadyLobbyResponse;
import org.springframework.integration.annotation.ServiceActivator;

@Slf4j
@AllArgsConstructor
public class ReadyLobbyLoggingHandler {

    @ServiceActivator
    void readyLobbyLogging(ReadyLobbyResponse response) {
        log.info("----------------");
        log.info("Lobby is ready with details: [{}]", response);
        log.info("----------------");
    }

}
