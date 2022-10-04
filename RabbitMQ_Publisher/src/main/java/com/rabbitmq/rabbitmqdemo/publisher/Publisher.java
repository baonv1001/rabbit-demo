package com.rabbitmq.rabbitmqdemo.publisher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.rabbitmqdemo.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Publisher {
    public static void main(String[] args) {
        ConnectionFactory factory = ConnectionUtil.buildConnectionFactory();
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
}
