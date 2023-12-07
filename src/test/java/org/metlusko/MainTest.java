package org.metlusko;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.metlusko.client.Client;
import org.metlusko.request.Request;
import org.metlusko.server.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;


class MainTest {
    private List<Integer> clientList = getList(100);
    private CountDownLatch countDownLatch = new CountDownLatch(clientList.size());
    private ReentrantLock lock = new ReentrantLock();
    private Random random = new Random();
    private List<Integer> listSever = new ArrayList<>();
    private ExecutorService executorService = Executors.newFixedThreadPool(clientList.size());

    @Test
    void main() {

        //Given
        Server server = new Server(listSever, lock, random);
        Request request = new Request(server, countDownLatch);
        Client client = new Client(clientList, executorService, random, request);

        //When
        client.doRequest();
        System.out.println("Accumulator :" + client.getAccumulator());

        //Then
        Assertions.assertEquals(5050, client.getAccumulator());
    }

    private static List<Integer> getList(int i) {
        List<Integer> list = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            list.add(j);
        }
        return list;
    }
}
