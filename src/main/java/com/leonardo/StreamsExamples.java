package com.leonardo;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class StreamsExamples {
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    public Integer totalize(int[] numbers) {
        return Arrays.stream(numbers)
                .sum();
    }

    public void multiTask(int i) throws InterruptedException {
        IntStream.range(0,i).forEach(e ->{
            this.executorService.execute(() -> {
                try {
                    System.out.println(e);
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            });
        });
        this.executorService.awaitTermination(2, TimeUnit.SECONDS);

    }
}
