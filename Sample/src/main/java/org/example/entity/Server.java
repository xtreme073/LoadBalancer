package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor

public class Server {
    String ip;
    int port;
    boolean isActive;

    public Server(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.isActive = true;
    }

}
