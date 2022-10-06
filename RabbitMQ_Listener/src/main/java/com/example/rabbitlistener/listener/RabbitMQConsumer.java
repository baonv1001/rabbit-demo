package com.example.rabbitlistener.listener;

import com.example.rabbitlistener.dto.Person;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
    @RabbitListener(queues = "sb.ac")
    public void getMessage(Person person) {
        System.out.println("Name: " + person.getName());
    }
}
