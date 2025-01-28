package org.example.lambdas.function;

import java.util.function.Function;

public class FunctionExample {
    public static void main(String[] args) {

        // Recibe un valor y retorna un resultado
        Function<Integer, String> function = (num) -> {
            return "El numero es: " + num;
        };

        //Resumido
        //Function<Integer, String> function = (num) ->  "El numero es: " + num;

        System.out.println(function.apply(2));


    }
}
