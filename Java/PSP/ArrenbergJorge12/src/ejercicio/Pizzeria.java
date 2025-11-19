package ejercicio;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Pizzeria {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println(" ** ABRIENDO LA PIZZERIA ** ");
		System.out.println("ENTRA UN CLIENTE ...");

		System.out.println("ENTRA EL COCINERO ...");

		for (int i = 0; i < 10; i++) {
			String nombre = String.format("%d", i);
			Pizza p = new Pizza();
			Cliente cl = new Cliente(nombre, p);
			Cocinero c = new Cocinero("Juan", p);
			System.out.println("METIENDO LA COMANDA EN COCINA ...");
			Bandeja.anyadirComanda(p);
			System.out.println("RECOGIENDO LA COMANDA ...");
			Bandeja.cogerComanda(p);
			
		}
	}

	public static String devolverString(Scanner sc, String mensaje) {
		String resultado = null;
		boolean valido = false;
		do {
			try {
				System.out.print(mensaje);
				resultado = sc.nextLine();
				valido = true;
			} catch (NoSuchElementException e) {
				System.out.println(e.getMessage());
			} catch (IllegalStateException e) {
				System.out.println(e.getMessage());
			}
		} while (!valido);
		return resultado;
	}
}
