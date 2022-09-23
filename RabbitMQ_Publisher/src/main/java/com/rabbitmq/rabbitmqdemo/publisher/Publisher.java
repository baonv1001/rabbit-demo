package com.rabbitmq.rabbitmqdemo.publisher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Publisher {
    public static void main(String[] args) {
        ConnectionFactory factory = buildConnectionFactory();
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()){
            String[] messages = new String[]{"one", "two", "three", "four"};
            for(String message : messages) {
                channel.basicPublish("", "MyQueue", null, message.getBytes());
            }
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    private static ConnectionFactory buildConnectionFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("eq-dev");
        factory.setPassword("eq-dev");
        factory.setVirtualHost("eq-dev");

        return factory;
    }
}
