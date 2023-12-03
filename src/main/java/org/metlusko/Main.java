package org.metlusko;

import org.metlusko.client.Client;
import org.metlusko.request.Request;
import org.metlusko.server.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = getList(100);

        Request request = new Request(new Random(), list);

        Server server = new Server(new ArrayList<>());
        Client client = new Client(list, request,server);

//        ThreadPool threadPool = new ThreadPool(client, server);
//        threadPool.processParallelyWithExecutorService();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(client);
        executorService.shutdown();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



//        client.processParallelyWithExecutorService();

//        executorService.submit(client::handleRequest);


        System.out.println("============server : "+server.getList().size());
        server.getList().forEach(System.out::println);
        System.out.println();

        System.out.println("client :" + client.getList().size());
        client.getList().forEach(System.out::println);



        /*extracted(client);
        System.out.println(server.getSortList());*/

    }





    private static List<Integer> getList(int i) {
        List<Integer> list = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            list.add(j);
        }
        return list;
    }
}
