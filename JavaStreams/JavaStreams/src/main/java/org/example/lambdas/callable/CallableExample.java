package org.example.lambdas.callable;

import java.util.concurrent.Callable;

public class CallableExample {
    public static void main(String[] args) {
        // No recibe valores, pero retorna un resultado y puede lanzar una excepcion.
        Callable<String> callable = () -> {
            return "Resultado de la tarea";
        };

        // Resumido
        //Callable<String> callable = () -> "Resultado de la tarea";

        try {
            String result = callable.call();
            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
