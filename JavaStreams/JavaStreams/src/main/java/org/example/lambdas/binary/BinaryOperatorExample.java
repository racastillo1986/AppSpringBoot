package org.example.lambdas.binary;

import java.util.function.BinaryOperator;

public class BinaryOperatorExample {
    public static void main(String[] args) {
        //Recibe 2 valores dl mismo tipo y retorna un valor del mismo tipo
        // BinaryOperator<Integer> se pone asi en lugar de asi BinaryOperator<Integer, Integer>
        // xq son del mismo tipo
        BinaryOperator<Integer> binaryOperator = (a, b) -> {
            return a + b;
        };
        int result = binaryOperator.apply(10, 20);
        System.out.println(result);
    }
}
