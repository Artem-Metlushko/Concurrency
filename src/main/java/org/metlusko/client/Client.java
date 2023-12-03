package org.metlusko.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.metlusko.request.Request;
import org.metlusko.server.Server;


import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@AllArgsConstructor
public class Client implements Runnable {

    private List<Integer> list;

    private final Lock lock = new ReentrantLock();
    private Request request;

    private Server server;


    @Override
    public void run() {
        while (!list.isEmpty()){
            int index = request.getRandomIndex();
            Integer removedValue = list.remove(index);
            System.out.println("Client remove :" + removedValue);
            server.handleRequest(removedValue);
        }

    }

}
