package org.metlusko.request;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Random;

@AllArgsConstructor
public class Request {

    private final Random random;
    private final List<Integer> list;

    public int getRandomIndex() {
        return random.nextInt(list.size());
    }
}
