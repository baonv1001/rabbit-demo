package com.example.rabbitlistener.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQExchangeConfiguration {
    @Bean
    public Exchange exampleExchange() {
        return new TopicExchange("ExampleExchange");
    }

    @Bean
    public Exchange example2Exchange() {
        return ExchangeBuilder.directExchange("Example2Exchange")
                .autoDelete()
                .internal()
                .build();
    }

    @Bean
    public Exchange newTopicExchange() {
        return ExchangeBuilder.topicExchange("TopicTestExchange")
                .autoDelete()
                .durable(true)
                .internal()
                .build();
    }

    @Bean
    public Exchange newFanOutExchange() {
        return ExchangeBuilder.fanoutExchange("FanoutTestExchange")
                .autoDelete()
                .durable(true)
                .internal()
                .build();
    }

    @Bean
    public Exchange newHeaderExchange() {
        return ExchangeBuilder.headersExchange("HeaderTestExchange")
                .internal()
                .durable(true)
                .ignoreDeclarationExceptions()
                .build();
    }
}
