package org.dzmitry.kapachou.integration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dzmitry.kapachou.integration.model.SearchingMatchmakingRequest;
import org.dzmitry.kapachou.integration.processor.SearchMatchmakingGateway;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@Slf4j
@EnableIntegration
@IntegrationComponentScan
@AllArgsConstructor
@SpringBootApplication
public class ApplicationRunner implements org.springframework.boot.ApplicationRunner {

    final SearchMatchmakingGateway searchMatchmakingGateway;

    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(ApplicationRunner.class).bannerMode(Banner.Mode.OFF).run(args);
    }

    @Override
    public void run(ApplicationArguments args) {
        SearchingMatchmakingRequest request = createSearchMatchmakingRequest();

        log.info("---------");
        log.info("SENDING A GENERATED REQUEST {}", request);
        log.info("---------");
        log.info("RESULT: {}", searchMatchmakingGateway.searchMatchmaking(request));
        log.info("---------");
    }

    private static SearchingMatchmakingRequest createSearchMatchmakingRequest() {
        SearchingMatchmakingRequest request = new SearchingMatchmakingRequest();
        request.setPlayerUUID("player-uuid-1");
        SearchingMatchmakingRequest.MatchmakingDetails matchmakingDetails = new SearchingMatchmakingRequest.MatchmakingDetails();
        matchmakingDetails.setMode(SearchingMatchmakingRequest.MatchmakingMode.DEATHMATCH);
        matchmakingDetails.setRanked(false);
        matchmakingDetails.setMapUUID("MAP-uuid-1");
        request.setMatchmakingDetails(matchmakingDetails);

        return request;
    }

}
