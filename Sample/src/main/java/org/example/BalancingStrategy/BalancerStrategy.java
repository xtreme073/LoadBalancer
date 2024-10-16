package org.example.BalancingStrategy;

import org.example.entity.Server;

import java.util.List;

public interface BalancerStrategy {

    Server getNextServer(List<Server> serverList);

}
