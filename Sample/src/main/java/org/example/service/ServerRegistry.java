package org.example.service;

import org.example.entity.Server;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServerRegistry {
    private List<Server> serverList;

    public ServerRegistry() {
        this.serverList = new ArrayList<>();
    }

    public void addServer(Server server) {
        serverList.add(server);
    }

    public void removeServer(Server server) {
        serverList.remove(server);
    }

    public void registerServer(String ip, int port) {
        // create server
        Server server = new Server(ip, port);
        addServer(server);
        System.out.println("New server added");
    }

    public void deRegisterServer(String ip, int port) {
        Server delete = null;
        // remove server
        for(Server server : serverList) {
            if(server.getIp().equalsIgnoreCase(ip) && server.getPort() == port)
                delete = server;
        }
        if(delete != null) {
            serverList.remove(delete);
            System.out.println("New server removed");
        }
        System.out.println("No server found to remove");
    }

    public  List<Server> getServer() {
        return serverList;

    }

}
