package org.metlusko.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.metlusko.request.Request;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

@Getter
@AllArgsConstructor
public class Client implements Runnable {

    private List<Integer> list;
    private Request request;
    private LinkedBlockingQueue <Integer> queue;


    @Override
    public void run() {
        while (!list.isEmpty()){
            int index = request.getRandomIndex();
            Integer removedValue = list.remove(index);

            try {
                queue.put(removedValue);
                System.out.println("Client remove :" + removedValue );
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
        }

    }

}
