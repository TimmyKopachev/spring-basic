package org.dzmitry.kapachou.aop.before;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class GameplayHandler {

    @Before(value = "execution(* org.dzmitry.kapachou.aop.before.DummyGameplayProcessor.start(..) )")
    public void gameplayExecutionProcess(JoinPoint joinPoint) {
        log.info("===BEFORE Aspect:===");
        DummyGameplayProcessor.Player player = (DummyGameplayProcessor.Player) joinPoint.getArgs()[0];
        log.info("Before starting, account for player [{}, {}] has to be created", player.getNickname(), player.getEmail());
    }
}
