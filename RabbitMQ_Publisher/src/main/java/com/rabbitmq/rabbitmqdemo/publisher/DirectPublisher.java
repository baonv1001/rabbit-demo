package com.rabbitmq.rabbitmqdemo.publisher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.rabbitmqdemo.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DirectPublisher {
    public static void main(String[] args) {
        ConnectionFactory factory = ConnectionUtil.buildConnectionFactory();
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()){
            String message = "This is mobile";

            channel.basicPublish("sb.direct", "tv", null, message.getBytes());
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
