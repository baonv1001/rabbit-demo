package com.rabbitmq.rabbitmqdemo.dto;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 5630224118077740919L;

    private Long id;
    private String name;

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
