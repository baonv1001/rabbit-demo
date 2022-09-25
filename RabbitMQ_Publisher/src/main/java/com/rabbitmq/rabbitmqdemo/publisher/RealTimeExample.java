package com.rabbitmq.rabbitmqdemo.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RealTimeExample {
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static void main(String[] args) {
        ConnectionFactory factory = buildConnectionFactory();
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()){
            ObjectNode json = objectMapper.createObjectNode();
            json.put("from_date", "01-Jan-2019");
            json.put("to_date", "31-Dec-2019");
            json.put("email", "abc@gmail.com");
            json.put("query", "SELECT * FROM data");

            channel.basicPublish("", "MyQueue", null, objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(json));
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
