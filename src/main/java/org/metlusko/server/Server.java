package org.metlusko.server;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

@Getter
@AllArgsConstructor
public class Server implements Runnable {
    private List<Integer> list;
    private LinkedBlockingQueue<Integer> queue;

    @Override
    public void run() {
        while ((!queue.isEmpty())) {
            try {
                int removedValue = queue.take();
                list.add(removedValue);
                System.out.println("Server add :" + removedValue + " " + list.size());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
