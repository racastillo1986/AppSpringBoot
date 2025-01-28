package org.example.lambdas.consumer;

import java.util.function.Consumer;

public class ConsumerExample {
    public static void main(String[] args) {
        /*
        // forma convencional
        Consumer<String> consumer = (param) -> {
            System.out.println(param);
        };
        */
        // resumido xq el mismo parametro se utiliza
        Consumer<String> consumer = System.out::println;

        consumer.accept("hola");
    }
}
