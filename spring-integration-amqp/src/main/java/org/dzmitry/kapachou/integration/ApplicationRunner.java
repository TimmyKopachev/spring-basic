package org.dzmitry.kapachou.integration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dzmitry.kapachou.integration.model.MatchmakingDetails;
import org.dzmitry.kapachou.integration.model.SearchingMatchmakingRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.support.json.JsonObjectMapper;
import org.springframework.integration.support.json.JsonObjectMapperProvider;

import java.io.IOException;
import java.util.Random;
import java.util.stream.LongStream;

import static org.dzmitry.kapachou.integration.model.MatchmakingMode.CAPTURE_FLAG;
import static org.dzmitry.kapachou.integration.model.MatchmakingMode.DEATHMATCH;

@Slf4j
@EnableIntegration
@IntegrationComponentScan
@AllArgsConstructor
@SpringBootApplication
public class ApplicationRunner implements org.springframework.boot.ApplicationRunner {

    final RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(ApplicationRunner.class).bannerMode(Banner.Mode.OFF).run(args);
    }

    @Override
    public void run(ApplicationArguments args) {
        JsonObjectMapper<?, ?> objectMapper = JsonObjectMapperProvider.newInstance();
        // 1-50 = 49 players to aggregate
        LongStream.range(1, 50).forEach(i -> {
            try {
                var message = objectMapper.toJson(createSearchMatchmakingRequest(i));
                log.info("sending request to find a matchmaking: {}", message);
                rabbitTemplate.convertAndSend("matchmaking.exchange", "matchmaking.routing.key", message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static SearchingMatchmakingRequest createSearchMatchmakingRequest(Long id) {
        var random = new Random();

        SearchingMatchmakingRequest request = new SearchingMatchmakingRequest();
        request.setPlayerUUID(String.format("player-uuid-%d", id));
        MatchmakingDetails matchmakingDetails = new MatchmakingDetails();
        matchmakingDetails.setMode(random.nextBoolean() ? DEATHMATCH : CAPTURE_FLAG);
        matchmakingDetails.setRanked(random.nextBoolean());
        matchmakingDetails.setMapUUID("MAP-uuid-1");
        request.setMatchmakingDetails(matchmakingDetails);

        return request;
    }

}
