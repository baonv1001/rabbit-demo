package com.example.rabbitlistener.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    @Bean
    public Queue exampleQueue() {
        return new Queue("ExampleQueue", false);
    }

    @Bean
    public Queue example2Queue() {
        return QueueBuilder.durable("Example2Queue")
                .autoDelete()
                .exclusive()
                .build();
    }
}
