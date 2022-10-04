package com.rabbitmq.rabbitmqdemo.util;

import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {
    private ConnectionUtil() {
    }

    public static ConnectionFactory buildConnectionFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("eq-dev");
        factory.setPassword("eq-dev");
        factory.setVirtualHost("sb-host");

        return factory;
    }
}
