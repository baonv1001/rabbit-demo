package com.rabbitmq.rabbitmqdemo.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RabbitMQConfiguration {
    public static final String EXCHANGE_NAME = "exchange";

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    @Bean
//    @DependsOn("amqpBroker")
    CachingConnectionFactory connectionFactory() {
        ConnectionFactory cf = new ConnectionFactory();
        cf.setHost("localhost");
        cf.setPort(5672);
        cf.setUsername("eq-dev");
        cf.setPassword("eq-dev");
        cf.setVirtualHost("sb-host");
        cf.setAutomaticRecoveryEnabled(false);

        return new CachingConnectionFactory(cf);
    }

    @Bean
    RabbitAdmin rabbitAdmin(org.springframework.amqp.rabbit.connection.ConnectionFactory  cf) {
        return new RabbitAdmin(cf);
    }

    @Bean
    RabbitTemplate rabbitTemplate(org.springframework.amqp.rabbit.connection.ConnectionFactory  cf,
                                  ObjectMapper mapper) {
        RabbitTemplate template = new RabbitTemplate(cf);
        RetryTemplate retry = new RetryTemplate();
        ExponentialBackOffPolicy backOff = new ExponentialBackOffPolicy();
        backOff.setInitialInterval(1000);
        backOff.setMultiplier(1.5);
        backOff.setMaxInterval(60000);
        retry.setBackOffPolicy(backOff);
        template.setRetryTemplate(retry);
        template.setMessageConverter(new Jackson2JsonMessageConverter(mapper));

        return template;
    }

    @Bean
    SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(org.springframework.amqp.rabbit.connection.ConnectionFactory  cf,
                                                                        ObjectMapper mapper) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(cf);
        factory.setMessageConverter(new Jackson2JsonMessageConverter(mapper));
        return factory;
    }

    @Bean
    Exchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }
}
