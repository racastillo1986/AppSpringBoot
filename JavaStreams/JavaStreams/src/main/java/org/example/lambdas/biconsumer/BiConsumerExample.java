package org.example.lambdas.biconsumer;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class BiConsumerExample {
    public static void main(String[] args) {

        // Recibe 2 valores y no retorna nada

        BiConsumer<String, String> biconsumer = (a, b) -> {
            System.out.println(a + " " + b);
        };

        // Resumido
        //BiConsumer<String, String> biconsumer2 = (a, b) -> System.out.println(a + " " + b);



        biconsumer.accept("aaa", "bbb");
    }
}
