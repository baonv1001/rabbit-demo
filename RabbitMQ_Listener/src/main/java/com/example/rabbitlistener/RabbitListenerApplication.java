package com.example.rabbitlistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Run Listener firstly
@SpringBootApplication
public class RabbitListenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitListenerApplication.class, args);
    }

}
