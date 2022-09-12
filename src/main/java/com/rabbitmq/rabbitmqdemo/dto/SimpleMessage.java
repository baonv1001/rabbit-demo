package com.rabbitmq.rabbitmqdemo.dto;

public class SimpleMessage {
    private String name;
    private String description;

    public SimpleMessage() {
    }

    public SimpleMessage(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
