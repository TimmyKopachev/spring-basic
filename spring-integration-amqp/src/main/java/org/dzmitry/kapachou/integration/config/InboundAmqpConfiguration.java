package org.dzmitry.kapachou.integration.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dzmitry.kapachou.integration.aggregation.CorrelationLobbyAggregationStrategy;
import org.dzmitry.kapachou.integration.aggregation.LobbyAggregationGroupProcessor;
import org.dzmitry.kapachou.integration.aggregation.ReleaseLobbyAggregationStrategy;
import org.dzmitry.kapachou.integration.config.properties.AmqpChannelProperties;
import org.dzmitry.kapachou.integration.model.SearchingMatchmakingRequest;
import org.dzmitry.kapachou.integration.processor.ReadyLobbyLoggingHandler;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

import java.util.List;

@Slf4j
@Configuration
@EnableConfigurationProperties(
        value = {
                AmqpChannelProperties.class,
        })
@AllArgsConstructor
public class InboundAmqpConfiguration implements InitializingBean {

    private final AmqpAdmin amqpAdmin;

    private final List<AmqpChannelProperties> amqpProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Setting up the Queue/Exchange/Binding for local environment");
        setupAmqpQueues();
    }
    
    public void setupAmqpQueues() {
        amqpProperties.forEach(amqpProperty -> {
            Queue queue = new Queue(amqpProperty.getQueue());
            TopicExchange topicExchange = new TopicExchange(amqpProperty.getExchange());
            amqpAdmin.declareQueue(queue);
            amqpAdmin.declareExchange(topicExchange);
            amqpAdmin.declareBinding(BindingBuilder.bind(queue).to(topicExchange).with(amqpProperty.getRoutingKey()));
        });
    }

    @Bean
    CustomDeserializer<?> deserializer() {
        return new CustomDeserializer<>(SearchingMatchmakingRequest.class);
    }

    @Bean
    IntegrationFlow amqpInbound(ConnectionFactory connectionFactory,
                                ReleaseLobbyAggregationStrategy releaseStrategy,
                                CorrelationLobbyAggregationStrategy correlationLobby,
                                LobbyAggregationGroupProcessor aggregationGroupProcessor) {
        return IntegrationFlows.from(Amqp.inboundAdapter(connectionFactory, "matchmaking"))
                .aggregate(aggregatorSpec -> aggregatorSpec
                        .releaseStrategy(releaseStrategy)
                        .correlationStrategy(correlationLobby)
                        .outputProcessor(aggregationGroupProcessor)
                        .expireGroupsUponCompletion(true)
                )
                .handle(new ReadyLobbyLoggingHandler())
                .get();
    }

}
