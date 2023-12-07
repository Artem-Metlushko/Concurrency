package org.metlusko.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.metlusko.server.Server;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

@Getter
@Setter
@RequiredArgsConstructor
public class Request implements Callable<Integer> {

    private final Server server;
    private final CountDownLatch latch;
    private int i;


    @Override
    public Integer call() {
        try {
            return server.response(i);
        } finally {
            latch.countDown();
            System.out.println("Counter: " + latch.getCount());
            System.out.println();
        }
    }
}
