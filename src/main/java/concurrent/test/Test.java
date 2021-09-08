package concurrent.test;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 3/16/2021
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(null, null);

        Consumer<String> consumer = o -> System.out.println(o);
        consumer.accept("hello!");

        AtomicInteger integer = new AtomicInteger();
        integer.getAndIncrement();
    }
}
