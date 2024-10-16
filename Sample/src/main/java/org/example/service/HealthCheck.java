package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.entity.LoadBalancer;
import org.example.entity.Server;
import org.example.service.LoadBalancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Data
@Component
public class HealthCheck {
    private LoadBalancer loadBalancer;
    private ServerRegistry serverRegistry;

    @Autowired
    public HealthCheck(LoadBalancer loadBalancer, ServerRegistry serverRegistry) {
        this.loadBalancer = loadBalancer;
        this.serverRegistry = serverRegistry;

    }

    @Scheduled(fixedRateString = "5000")
    private void checkServer() {

        for(Server server : serverRegistry.getServer()) {
            boolean active = pingServer(server);
            server.setActive(active);
            if(active) {
                System.out.println("server " + server.getIp() + " is active");
            } else {
                System.out.println("server " + server.getIp() + " is not active");
            }
        }

    }

    private boolean pingServer(Server server) {
        return Math.random() >0.2;
    }
}
