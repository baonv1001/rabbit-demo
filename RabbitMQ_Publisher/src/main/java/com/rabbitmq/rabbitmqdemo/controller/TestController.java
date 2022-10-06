package com.rabbitmq.rabbitmqdemo.controller;

import com.rabbitmq.rabbitmqdemo.dto.Person;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TestController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/test/{name}")
    public String testAPI(@PathVariable("name") String name) {
        System.out.println("Name: " + name);
        Person person = new Person(1L, name);
        rabbitTemplate.convertAndSend("sb.mobile", person);
//        rabbitTemplate.convertAndSend("sb.direct", "tv", person);
        return "Success";
    }
}
