package com.example.demo.controller;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.example.demo.model.Message;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import service.SocketIOService;

@Log4j2
@Import(SocketIOService.class)
@Controller
public class SocketIOController {

    @Autowired
    private SocketIOService socketIOService;

    // Handle connection event
    @OnConnect
    public void onConnect(SocketIOClient client) {
        log.info("Perform operation on user connect in controller");
    }

    // Handle disconnection event
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        log.info("Perform operation on user disconnect in controller");
    }

    // Handle custom event
    @OnEvent("messageSendToUser")
    public void onMessageSendToUser(SocketIOClient client, AckRequest ackRequest, Message data) {
        socketIOService.sendMessageToUser(client, ackRequest, data);
    }

}
