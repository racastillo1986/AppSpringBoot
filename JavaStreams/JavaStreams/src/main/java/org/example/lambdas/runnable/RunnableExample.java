package org.example.lambdas.runnable;

public class RunnableExample {
    public static void main(String[] args) {
        // No recibe valores, no retorna nada, solo ejecuta una tarea
        Runnable runnable = () -> {
            System.out.println("Ejecutando tarea...");
        };
        runnable.run();
    }
}
