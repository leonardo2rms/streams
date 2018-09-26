package com.leonardo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StreamsExamplesTests {
    private StreamsExamples examples = new StreamsExamples();


    @Before
    public void setUp() {

    }

    @Test
    public void Should_SumNumbers_WhenSendArrayOfNumbers() {
        int total = examples.totalize(new int[]{1, 2, 3, 4, 5, 6});

        Assert.assertEquals(21, total);
    }

    @Test
    public void Should_MultitaskHeavyWork_WhenCalled() throws InterruptedException {
        examples.multiTask(100);
    }
}
