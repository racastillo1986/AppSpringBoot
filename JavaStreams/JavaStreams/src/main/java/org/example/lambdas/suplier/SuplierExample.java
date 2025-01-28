package org.example.lambdas.suplier;

import java.util.function.Supplier;

public class SuplierExample {
    public static void main(String[] args) {

        // No recibe ningun valor, pero retorna un resultado
        Supplier<String> supplier = () -> {
            return "Hola, soy un Supplier";
        };

        //Resumido
        //Supplier<String> supplier = "Hola, soy un Supplier";

        System.out.println(supplier.get());
    }
}
