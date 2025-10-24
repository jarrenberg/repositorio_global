package ejercicio1;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EjemploProcessBuilder {

	public static void main(String[] args) throws IOException, InterruptedException {

		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduzca un programa para abrir: ");
		String pUser = null;

		try {
			pUser = pedirPrograma(teclado);
		} catch (IllegalStateException ex) {
			System.out.println("Ha ocurrido un error.");
		} catch (NoSuchElementException ex) {
			System.out.println("Ha ocurrido un error.");
		}

		ProcessBuilder pb = new ProcessBuilder("calc");
		Process p1 = pb.start();

		System.out.printf("El PID de la calculadora es: %d\n",p1.pid());
		System.out.println(p1.isAlive());

		pb = pb.command("notepad");
		p1 = pb.start();

		System.out.printf("El PID del bloc de notas: %d\n",p1.pid());
		System.out.println(p1.isAlive());

		pb = pb.command(pUser);
		p1 = pb.start();

		System.out.printf("El PID del programa introducido por teclado es: %d\n",p1.pid());
		System.out.printf("El PID del p	rograma introducido por teclado es: %d\n",p1.pid());
		System.out.println(p1.isAlive());
		
		

	}

	public static String pedirPrograma(Scanner teclado) throws IllegalStateException, NoSuchElementException {
		String resultado = null;
		try {
			resultado = teclado.nextLine();
		} catch (IllegalStateException ex) {
			System.out.println("Ha ocurrido un error.");
		} catch (NoSuchElementException ex) {
			System.out.println("Ha ocurrido un error.");
		}
		return resultado;
	}
}
