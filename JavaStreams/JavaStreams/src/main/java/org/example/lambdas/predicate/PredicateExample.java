package org.example.lambdas.predicate;

import java.util.function.Predicate;

public class PredicateExample {
    public static void main(String[] args) {

        // Recibe un valor y retorna un booleano
        Predicate<String> predicate = (str) -> {
            return str.length() > 5;
        };

        //Resumido
        // Predicate<String> predicate = (str) -> str.length() > 5;

        System.out.println(predicate.test("Hola"));
    }
}
