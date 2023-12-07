package org.metlusko.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.metlusko.request.Request;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@RequiredArgsConstructor
public class Client {

    private final List<Integer> dataList;
    private final ExecutorService executorService;
    private final Random random;
    private final Request request;
    private int accumulator;


    public void doRequest() {

        int size = dataList.size();

        for (int i = 1; i <= size; i++) {
            sleep();

            int index = random.nextInt(dataList.size());
            int value = dataList.remove(index);
            System.out.println("Client remove : " + value);

            request.setI(value);
            Integer localVal = submit(request);

            setAccumulator(accumulator += localVal);
        }

        shutDown();
    }


    private void shutDown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    private void sleep() {
        try {
            int time = random.nextInt(10);
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private Integer submit(Request task) {

        try {
            return  executorService.submit(task).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


}
