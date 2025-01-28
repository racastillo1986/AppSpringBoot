package org.example.lambdas.bifunction;

import java.util.function.BiFunction;

public class BiFunctionExample {
    public static void main(String[] args) {
        //Recibe 2 valores y retorna un resultado
        BiFunction<Integer, Integer, Integer> biFunction = (a, b) -> {
            return a + b;
        };

        //Resumido
        //BiFunction<Integer, Integer, Integer> biFunction = (a, b) ->  a + b;

        System.out.println(biFunction.apply(10, 20));
    }

}
