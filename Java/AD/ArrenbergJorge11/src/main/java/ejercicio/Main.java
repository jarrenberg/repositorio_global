package ejercicio;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		menu();
	}

	public static void menu() {
		boolean salir = false;
		do {
			try (Scanner sc = new Scanner(System.in)) {
				System.out.println("*** MENU ***");
				System.out.println("1) Mostrar tabla");
				System.out.println("2) Añadir elemento a equipos");
				System.out.println("3) Añadir elemento a piezas");
				System.out.println("4) Actualizar elemento de equipos");
				System.out.println("5) Actualizar elemento de piezas");
				System.out.println("6) Eliminar elemento de equipos");
				System.out.println("7) Eliminar elemento de piezas");
				System.out.println("8) Salir");

				int accion = UtilidadesSQLite.devolverEntero(sc, "Introduzca la accion:");
				if (accion == 1) {
					UtilidadesSQLite.mostrarTodosRegistrosTabla("piezas");
					salir=true;
				}
			}
		} while (!salir);
	}
}
