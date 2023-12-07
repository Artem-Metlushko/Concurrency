package org.metlusko.server;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;

@AllArgsConstructor
public class Server {
    private final List<Integer> list;
    private final Lock lock;
    private final Random random;

    public int response(int value) {
        lock.lock();
        sleep();
        try {
            list.add(value);
            System.out.println("Server add :" + value );
        } finally {
            lock.unlock();
        }
        return list.size();
    }

    private void sleep() {
        try {
            int time = random.nextInt(10) + 100;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
