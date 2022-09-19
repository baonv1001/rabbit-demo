package com.rabbitmq.rabbitmqdemo;

import com.rabbitmq.rabbitmqdemo.dto.SimpleMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMqDemoApplication implements CommandLineRunner {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SimpleMessage simpleMessage = new SimpleMessage("message name", "the first message");
        rabbitTemplate.convertAndSend("TestExchange", "testRouting", simpleMessage);
    }
}
