package com.example.websockectandconcurrentlogin.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Alarm {

    private String content;
    private String sender;
    private String type;

    public enum MessageType {
        CHAT, LEAVE, JOIN, RESERVATION
    }
}
