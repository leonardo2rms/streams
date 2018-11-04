package com.leonardo;

import com.leonardo.mock.MockBuilders;
import com.leonardo.model.User;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsExamplesTests extends MockBuilders {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ExecutorService executorService = Executors.newFixedThreadPool(10);
    private int[] intArray = new int[]{1, 2, 3, 4, 5, 6};

    @Before
    public void setUp() {
    }

    @Test
    public void testReduceSum() {
        //Utilizando el sum, si la lista es nula el resultado es 0
        Integer total = Arrays.stream(new int[]{-1})
                .sum();
        logger.info(total.toString());
    }

    @Test
    public void testReduce() {
        //Cuando el identity no esta presenta en el reduce, el resultado siempre sera optional porque la lista
        //de numeros puede ser nula
        OptionalInt sum = Arrays.stream(intArray)
                .reduce((i1, i2) -> i1 + i2);
        logger.info(sum.toString());
    }

    @Test
    public void testReduceWithIdentity() {
        //Cuando el identity esta presente en el reduce, se toma ese valor como inicial, por lo tanto siempre devolvera
        //un resultado y no debe manejarse con un optional
        //el metodo reduce del stream recibe un BinaryOperator, se puede mandar esta funcion o crearlo como anonima en un
        //lambda como lo hice en el stream de abajo
        BinaryOperator<Integer> sumIntegers = (i1, i2) -> i1 + i2;

        Integer sum = Arrays.stream(intArray)
                .reduce(0, (i1, i2) -> i1 + i2);
        logger.info(sum.toString());
    }

    @Test
    public void testReduceMax(){
        OptionalInt max = Arrays.stream(intArray)
                .max();
        logger.info(max.toString());
    }

    @Test
    public void testFlatMap() {
        //El flat map sirve para planchar varios streams en 1, por ejemplo, en este caso tengo varias listas de
        //phone nombers, las agrego todas en un mismo stream con el flatmap y las manejo como si fuesen una
        List<Integer> numbers = buildUsers().stream()
                .map(e -> e.getPhoneNumbers().stream())
                .flatMap(e -> e)
                .collect(Collectors.toList());
        logger.info(numbers.toString());
    }

    @Test
    public void testOtherFlatMap() {
        //En este ejemplo tengo en un stream dos listas de usuarios, utilizo el flatmap y lo convierto en una unica lista
        //luego lo convierto en un stream, quedando asi un Stream<User> para manipularlo con otras operaciones sobre el stream
        List<String> names = Stream.of(buildUsers(), buildUsers())
                .flatMap(e -> e.stream())
                .map(e -> e.getName())
                .collect(Collectors.toList());
        logger.info(names.toString());
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
