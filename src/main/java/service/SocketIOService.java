package service;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.example.demo.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SocketIOService {

    @Autowired
    private SocketIOServer socketIOServer;

    public void sendMessageToUser(SocketIOClient client, AckRequest ackRequest, Message data) {
        log.info(String.format("%s send message to user %s and content is [%s]", data.getSenderName(), data.getRecipientName(), data.getContent()));
        socketIOServer.getBroadcastOperations().sendEvent(data.getRecipientName(), client, data);

        ackRequest.sendAckData("Message send to target user successfully");
    }

}
