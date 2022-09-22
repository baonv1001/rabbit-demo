package com.example.rabbitlistener.listener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = buildConnectionFactory();
        /*try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()){
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody());
                System.out.println("Message received = " + message);
            };

            channel.basicConsume("MyQueue", true, deliverCallback, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }*/

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("Message received = " + message);
        };

        channel.basicConsume("MyQueue", true, deliverCallback, consumerTag -> {});
//        channel.basicConsume("MyQueue", false, deliverCallback, consumerTag -> {});
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
