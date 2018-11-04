package com.leonardo;

import com.leonardo.mock.MockBuilders;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamsExamplesTests extends MockBuilders {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ExecutorService executorService = Executors.newFixedThreadPool(10);
    private int[] intArray = new int[]{1, 2, 3, 4, 5, 6};

    @Before
    public void setUp() {
    }

    @Test
    public void Should_SumNumbers_WhenSendArrayOfNumbers() {
        Integer total = Arrays.stream(intArray)
                .sum();
        logger.info(total.toString());
    }

    @Test
    public void testFlatMap() {
        List<Integer> numbers = buildUsers().stream()
                .map(e -> e.getPhoneNumbers().stream())
                .flatMap(e -> e)
                .collect(Collectors.toList());
        logger.info(numbers.toString());
    }

    @Test
    public void multiTask() throws InterruptedException {
        IntStream.range(0, 10).forEach(e -> {
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
