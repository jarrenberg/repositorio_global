package ArrenbergJorge.ejercicio;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
	private static final int accionMin = 1;
	private static final int accionMax = 5;

	public static void main(String[] args) {
		menu();

	}

	public static void menu() {
		try (Scanner sc = new Scanner(System.in)) {
			boolean fin = false;
			do {
				System.out.println("\n-- INTRODUZCA UNA ACCION --\n");
				System.out.println("1) MOSTRAR ARTICULOS");
				System.out.println("2) MOSTRAR VENTAS");
				System.out.println("3) AÑADIR ARTICULO");
				System.out.println("4) VENDER ARTICULO");
				System.out.println("5) SALIR");

				int accion = 0;
				while ((accion < accionMin) || (accion > accionMax)) {
					accion = UtilidadesSQL.devolverEntero(sc, "Introduzca una accion: ");
				}
				switch (accion) {
				case 1:
					UtilidadesSQL.mostrarTodosRegistrosTabla("articulo");
					break;
				case 2:
					UtilidadesSQL.mostrarTodosRegistrosTabla("ventas");
					break;
				case 3:
					int id = UtilidadesSQL.devolverEntero(sc, "Introduzca la ID del articulo: ");
					String nombre = UtilidadesSQL.devolverString(sc, "Introduzca el nombre del articulo: ");
					int existencias = UtilidadesSQL.devolverEntero(sc, "Introduzca las existencias del articulo: ");
					double precio = UtilidadesSQL.devolverDouble(sc, "Introduzca el precio del articulo: ");
					SQLTransacciones.addArticulo(id, nombre, existencias, precio);
					break;
				case 4:
					int idArticulo = UtilidadesSQL.devolverEntero(sc, "Introduzca el ID del articulo vendido: ");
					int cantidad = UtilidadesSQL.devolverEntero(sc, "Introduzca la cantidad vendida: ");
					LocalDate fecha = UtilidadesSQL.devolverFecha(sc,
							"Introduzca la fecha en la que se realiza la venta (YYYY-MM-DD): ");
					SQLTransacciones.addVenta(idArticulo, cantidad, fecha);
					break;
				case 5:
					fin = true;
					System.out.println("\nSaliendo ...");
					break;
				}
			} while (!fin);
		}
	}

}
