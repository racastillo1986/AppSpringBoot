package org.example.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Ana", "Luis", "Maria", "Pedro", "Juan", "Carla");

        //Operadores en streams

        //filter(): filtra los elementos que cumplen una condicion
        System.out.println("********************* filter() *************************");
        names.stream()
                .filter((name) -> name.length() > 4)
                .forEach(System.out::println);

        // map(): Transforma los elementos aplicando una funcion(modifica elementos)
        System.out.println("********************* map() *************************");
        //Resumido -> .map(String::toUpperCase)
        names.stream()
                .map((name) -> {
                    return name.toUpperCase();
                })
                .forEach(System.out::println);

        // map(): y luego filter()
        System.out.println("********************* map() y filter() *************************");
        names.stream()
                .map(String::toUpperCase)
                .filter((name) -> name.startsWith("A"))
                .forEach(System.out::println);

        // sorted(): Ordena los elementos del stream
        System.out.println("********************* sorted() *************************");
        names.stream()
                .sorted()
                .forEach(System.out::println);

        // forEach()
        System.out.println("********************* forEach() *************************");
        names.stream()
                .forEach((name) -> {
                    System.out.println(name);
                });

        // reduce(): Combina todos los elementos en un solo valor
        System.out.println("********************* reduce() *************************");
        String result = names.stream()
                .reduce("Resultado: ", (a, b) -> a + " " + b);
        System.out.println(result);

        // collect(): Recoge todos los elementos en una coleccion
        System.out.println("********************* collect() *************************");
        List<String> result1 = names.stream()
                .map((name) -> name.toUpperCase())
                .collect(Collectors.toList()); //.toList(); a partir de java17

        result1.stream().forEach(System.out::println);

        // distinct(): Elimina los duplicados
        System.out.println("********************* distinct() *************************");
        names.stream()
                .distinct()
                .forEach(System.out::println);

        // limit(): Limita el numero de elementos procesados
        System.out.println("********************* limit() *************************");
        names.stream()
                .limit(3)
                .forEach(System.out::println);

        // skip(): Omite numero especifico de elementos
        System.out.println("********************* skip() *************************");
        names.stream()
                .skip(3)
                .forEach(System.out::println);

        //anyMatch(): Verifica si algun elemento cumple una condicion
        // Resumido: .anyMatch((name) -> name.startsWith("J"))
        System.out.println("********************* anyMatch() *************************");
        boolean result2 = names.stream()
                .anyMatch((name) -> {
                    return name.startsWith("J");
                });
        System.out.println(result2);

        //allMatch(): Verifica si TODOS los elementos cumplen la condicion
        System.out.println("********************* allMatch() *************************");
        boolean result3 = names.stream()
                .allMatch((name) -> name.startsWith("A"));
        System.out.println(result3);

        //noneMatch(): Verifica si NINGUN elemento cumple la condicion
        System.out.println("********************* anoneMatch() *************************");
        boolean result4 = names.stream()
                .noneMatch((name) -> name.length() == 10);
        System.out.println(result4);
    }
}
