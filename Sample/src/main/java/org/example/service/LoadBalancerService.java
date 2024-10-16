package org.example.service;

import org.example.BalancingStrategy.RoundRobinStrategy;
import org.example.entity.LoadBalancer;
import org.example.entity.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class LoadBalancerService {
    private final LoadBalancer loadBalancer;
    private final HealthCheck healthCheck;
    private final ServerRegistry serverRegistry;

    @Autowired
    public LoadBalancerService(LoadBalancer loadBalancer, HealthCheck healthCheck, ServerRegistry serverRegistry) {
        this.healthCheck = healthCheck;
        this.loadBalancer = loadBalancer;
        this.serverRegistry = serverRegistry;
    }


    @PostConstruct
    public void initialise() {

        try {
            loadBalancer.setStrategy(new RoundRobinStrategy());

            serverRegistry.registerServer("a", 1);
            serverRegistry.registerServer("b", 2);
            serverRegistry.registerServer("c", 3);

            for(int i=0;i<5;i++) {
                loadBalancer.handleRequest();
            }

            loadBalancer.switchRandom();

            for(int i=0;i<5;i++) {
                loadBalancer.handleRequest();
            }


        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getStackTrace());
        }
        //register servers

        System.out.println("Hello world!");

    }

    public LoadBalancer getLoadBalancer() {
        return  loadBalancer;
    }


}
