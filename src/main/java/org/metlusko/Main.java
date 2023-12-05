package org.metlusko;

import org.metlusko.client.Client;
import org.metlusko.request.Request;
import org.metlusko.server.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = getList(100);

        Request request = new Request(new Random(), list);

        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        List<Integer> serverList = new ArrayList<>();

        Client client = new Client(list, request, queue);
        Server server = new Server(serverList, queue);


        ExecutorService executorService = Executors.newFixedThreadPool(20);

        executorService.execute(client);

        Future<?> future = executorService.submit(client);

        while (!future.isDone()) {
            executorService.execute(server);
        }

        executorService.shutdown();


    }


    private static List<Integer> getList(int i) {
        List<Integer> list = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            list.add(j);
        }
        return list;
    }
}
