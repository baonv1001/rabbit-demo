package com.example.rabbitlistener.config;

import com.example.rabbitlistener.listener.RabbitMQMessageListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    /**
     * 1. Define the Queue to listen to
     * 2. Provide the Connection to the Queue
     * 3. Bind the Queue, Connection and Listener
     */

    private static final String MY_QUEUE = "MyQueue";

    @Bean
    public Queue myQueue() {
        return new Queue(MY_QUEUE, true);
    }

    @Bean
    public Exchange myExchange() {
        return ExchangeBuilder.topicExchange("MyTopicExchange")
                .durable(true)
                .build();
    }

    @Bean
    public Binding binding() {
//        return new Binding(MY_QUEUE, Binding.DestinationType.QUEUE, "MyTopicExchange", "topic", null);

        return BindingBuilder
                .bind(myQueue())
                .to(myExchange())
                .with("topic")
                .noargs();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("eq-dev");
        cachingConnectionFactory.setPassword("eq-dev");
        cachingConnectionFactory.setVirtualHost("eq-dev");

        return cachingConnectionFactory;
    }

    @Bean
    public MessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setQueues(myQueue());
        simpleMessageListenerContainer.setMessageListener(new RabbitMQMessageListener());

        return simpleMessageListenerContainer;
    }
}
