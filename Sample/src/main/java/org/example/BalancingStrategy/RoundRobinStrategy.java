package org.example.BalancingStrategy;

import org.example.entity.Server;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("roundRobinStrategy")
public class RoundRobinStrategy implements  BalancerStrategy {
    private  int currentIndex = -1;
    @Override
    public synchronized Server getNextServer(List<Server> serverList) {
        if(serverList.isEmpty()) {
            throw new IllegalStateException();
        }
        currentIndex = (currentIndex +  1) % serverList.size();
        return serverList.get(currentIndex);
    }
}
