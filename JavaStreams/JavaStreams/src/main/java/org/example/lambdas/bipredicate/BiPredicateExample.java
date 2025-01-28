package org.example.lambdas.bipredicate;

import java.util.function.BiPredicate;

public class BiPredicateExample {
    public static void main(String[] args) {
        // Recibe 2 valores y retorna un Booleano
        BiPredicate<Integer, Integer> biPredicate = (a, b) -> {
            return a > b;
        };

        // Resumido
        // BiPredicate<Integer, Integer> biPredicate = (a, b) ->  a > b;

        boolean result = biPredicate.test(10, 5);
        System.out.println(result);
    }
}
