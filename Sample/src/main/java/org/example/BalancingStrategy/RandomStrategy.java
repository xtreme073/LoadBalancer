package org.example.BalancingStrategy;

import org.example.entity.Server;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@Qualifier("randomStrategy")
public class RandomStrategy implements BalancerStrategy{
    private final Random random = new Random();

    @Override
    public synchronized Server getNextServer(List<Server> serverList) {
        if(serverList.isEmpty()) {
            throw new IllegalStateException();
        }
        int randomIndex = random.nextInt(serverList.size());
        return serverList.get(randomIndex);
    }
}
