package org.metlusko.server;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@Getter
@AllArgsConstructor
public class Server {
//    private final Lock lock = new ReentrantLock();
    private List<Integer> list;

//    public void processRequest(Integer removedValue) {
//        if(lock.tryLock()){
//            try {
//                System.out.println("Processing request: " + removedValue);
//                list.add(removedValue);
//            } finally {
//                lock.unlock();
//            }
//        }
//    }

    public void handleRequest(Integer removedValue) {
        System.out.println("Server add :" + removedValue);
        list.add(removedValue);
    }

}
