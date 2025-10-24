package ejercicio;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ejecucion extends Thread {
    public static void main(String[] args) {
        // Creamos una lista que albergara los objetos Raton
        ArrayList<Raton> lista = new ArrayList<>();

        // Añadimos a la lista 4 objetos de tipo Raton
        lista.add(new Raton("Tinky", 4));
        lista.add(new Raton("Winky", 2));
        lista.add(new Raton("Poo", 5));
        lista.add(new Raton("Lala", 1));

        // Mostramos el tiempo que tardaria en ejecturse de forma lineal
        int tiempo = lista.stream().mapToInt((raton) -> raton.getTiempo()).sum();
        System.out.printf("\n--- Tiempo que tardaría en ejecutarse de manera lineal: %ds ---\n\n", tiempo);

        // Creamos una variable que almacene la fecha justo cuando vaya a
        // ejecutarse el hilo
        LocalDateTime inicio = LocalDateTime.now();

        // Recorremos la lista mediante el metodo for each con una expresion lambda,
        // iniciando cada objeto como un hilo con el metodo start
        try {
            lista.forEach((raton) -> {
                raton.start();
            });
        } catch (IllegalThreadStateException ex) {
            System.out.println(ex.toString());
        }

        // Esperamos a que cada hilo termine
        lista.forEach((raton) -> {
            try {
                raton.join();
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
        });
        LocalDateTime fin = LocalDateTime.now();// Creamos una variable que almacene la fecha justo cuando termine de
                                                // ejecutarse el hilo
        System.out.printf("\n--- Tiempo que ha tardado en ejecutarse con hilos: %ds ---\n",
                Duration.between(inicio, fin).toSeconds());
        /*
         * Creado de forma "tradicional" con un bucle for each
         * for (Raton raton : lista) {
         * raton.start();
         * }
         */

    }
}
