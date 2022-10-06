//package com.rabbitmq.rabbitmqdemo.listener;
//
//import com.rabbitmq.rabbitmqdemo.dto.Person;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class RabbitMQConsumer {
//    @RabbitListener(queues = "sb.ac")
//    public void getMessage(Person person) {
//        System.out.println("Listener with name: " + person.getName());
//    }
//}
