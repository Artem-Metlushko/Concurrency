package org.metlusko;

import lombok.AllArgsConstructor;
import org.metlusko.client.Client;
import org.metlusko.server.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@AllArgsConstructor
public class ThreadPool {
    private Client client;
    private Server server;

//    public void processParallelyWithExecutorService() throws InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        List<CompletableFuture<Void>> futures = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//
//                    Integer removedValue = client.request();
//                    if(removedValue!=null){
//                        server.handleRequest(removedValue);
//                    }
//
//            }, executorService);
//            futures.add(future);
//        }
//        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
//        executorService.shutdown();
//    }



}
