package ejercicio1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// Guardamos la fecha y hora en este momento para calcular luego el tiempo de
		// ejecucion
		LocalDateTime inicio = LocalDateTime.now();
		Scanner sc = new Scanner(System.in);

		// Pedimos al usuario que introduzca los nombres de los tres hilos que vamos a
		// crear
		String[] nombres = listaNombres(sc, 3);

		// Creamos 3 objetos TareaHilo con los nombres introducidos por el usuario
		// previamente y los guardamos en un array
		TareaHilo[] hilos = new TareaHilo[3];
		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new TareaHilo(nombres[i]);
		}

		// Creamos 3 objetos Thread con los objetos TareaHilo, los guardamos en un
		// array y los iniciamos
		Thread[] threads = new Thread[3];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(hilos[i]);
			threads[i].start();
		}

		// Esperamos a que todos los hilos terminen
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}

		// Guardamos la fecha y hora de cuando terminan de ejecutarse los hilos
		LocalDateTime fin = LocalDateTime.now();

		// Calculamos la diferencia de tiempo entre el inicio del programa y el final
		System.out.printf("Tiempo de ejecucion en ms= %d\n", (Duration.between(inicio, fin).toMillis()));
	}

	// Metodo que te devuelve una lista de nombres del tamaño indicado como parametro
	public static String[] listaNombres(Scanner sc, int tamanyo) {
		// Creamos un array del tamaño pasado como parametro
		String[] resultado = new String[tamanyo];
		int i = 0;
		//Seguimos pidiendo nombres al usuario mientras el array no este completo
		while (i < tamanyo) {
			try {
				System.out.println("Introduzca un nombre para el hilo: ");
				String nombre = sc.nextLine();
				resultado[i] = nombre;
				i++;
			} catch (NoSuchElementException e) {
				System.out.println(e.getMessage());
			} catch (IllegalStateException e) {
				System.out.println(e.getMessage());
			}

		}
		return resultado;
	}
}
