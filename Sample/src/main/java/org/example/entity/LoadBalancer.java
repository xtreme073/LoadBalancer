package org.example.entity;

import lombok.Data;
import org.example.BalancingStrategy.BalancerStrategy;
import org.example.BalancingStrategy.RandomStrategy;
import org.example.BalancingStrategy.RoundRobinStrategy;
import org.example.service.ServerRegistry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class LoadBalancer {

    private ServerRegistry serverRegistry;
    private BalancerStrategy balancerStrategy;

    public LoadBalancer(@Qualifier("roundRobinStrategy") BalancerStrategy balancerStrategy,
                        ServerRegistry serverRegistry) {
        this.balancerStrategy = balancerStrategy;
        this.serverRegistry = serverRegistry;
    }

    public void setStrategy(BalancerStrategy strategy) {
        this.balancerStrategy = strategy;
    }

    public Server getNextServer() {
        List<Server> servers = serverRegistry.getServer();
        return balancerStrategy.getNextServer(servers);
    }

    public void handleRequest() {
        Server selectedServer = getNextServer();
        System.out.println("forwarding Request to server " + selectedServer.getIp());
    }

    public void switchRandom() {
        setStrategy(new RandomStrategy());
    }
}
