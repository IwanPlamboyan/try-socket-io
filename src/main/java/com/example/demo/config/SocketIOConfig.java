package com.example.demo.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SocketIOConfig {

    @Value("${socket.host}")
    private String socketHost;

    @Value("${socket.port}")
    private int socketPort;

    private SocketIOServer server;

    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(socketHost);
        config.setPort(socketPort);
        //config.setOrigin();

        server =  new SocketIOServer(config);
        server.start();
        return server;
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketIoServer) {
        return new SpringAnnotationScanner(socketIoServer);
    }

    @PreDestroy
    public void stopSocketIOServer() {
        this.server.stop();
    }

}
