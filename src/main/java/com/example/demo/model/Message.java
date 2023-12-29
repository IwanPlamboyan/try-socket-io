package com.example.demo.model;

import lombok.Data;

@Data
public class Message {
    private String senderName;
    private String recipientName;
    private String content;
    private String room;
}
