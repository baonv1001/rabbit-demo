package com.rabbitmq.rabbitmqdemo.publisher;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.rabbitmqdemo.util.ConnectionUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class HeadersPublisher {
    public static void main(String[] args) {
        ConnectionFactory factory = ConnectionUtil.buildConnectionFactory();
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            String message = "Message for Mobile and TV";
            Map<String, Object> map = new HashMap<>();
            map.put("item1", "mobile");
            map.put("item2", "television");
            AMQP.BasicProperties basicProperties = new AMQP.BasicProperties()
                    .builder().headers(map).build();

            channel.basicPublish("sb.headers", "", basicProperties, message.getBytes());
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
