package ejercicio;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		menu();
	}

	// Metodo que muestra un menu y ejecuta las acciones dependiendo de la
	// instruccion introducida
	public static void menu() {
		// Flag para saber cuando dejar de mostrar el menu
		boolean salir = false;
		try (Scanner sc = new Scanner(System.in)) {
			// Mostramos el menu y ejecutamos la accion hasta que se introduzca el numero 4
			do {
				System.out.println("\n\nMENU DE OPCIONES:");
				System.out.println("1) CONOCER EL NUMERO DE PLAZAS DISPONIBLES DE UN DESTINO");
				System.out.println("2) CREAR UNA RESERVA");
				System.out.println("3) CANCELAR UNA RESERVA");
				System.out.println("4) SALIR\n");
				// Guardamos la accion introducida por el en una variable
				int accion = UtilidadesSQLite.devolverEntero(sc, "ACCION:");

				// Ejecutamos cierta instruccion dependiendo de la accion introducida pro el
				// usuario
				switch (accion) {
				case 1:
					// Pedimos al usuario que introduzca el distino del cual quiere saber cuantas
					// plazas libres hay y lo almacenamos en una variable
					String destino = UtilidadesSQLite.devolverString(sc, "Introduzca el destino: ");
					// Guardamos en una variable el resultado del metodo que nos devuelve las plazas
					// libres del destino introducido como parametro
					int plazasLibres = Viaje.plazasLibres(destino);
					// Interpretamos el resultado, ya que sabemos que si las plazas libres son
					// negativas significa que no existe dicho destino y si son positivas mostramos
					// el valor
					if (plazasLibres != (-1)) {
						System.out.printf("Las plazas libres del destino %s son: %d", destino, plazasLibres);
					} else {
						System.out.println("No existe dicho destino");
					}
					break;
				case 2:
					// Pedimos al usuario que introduzca el distino al cual quiere realizar la
					// reserva,su codigo de cliente y el numero de plazas que quiere reservar y lo
					// almacenamos en las variables
					destino = UtilidadesSQLite.devolverString(sc, "Introduzca el destino: ");
					int codigoCliente = UtilidadesSQLite.devolverEntero(sc, "Introduzca el codigo del cliente: ");
					int plazasCliente = UtilidadesSQLite.devolverEntero(sc,
							"Introduzca las plazas que quiere reservar el cliente: ");
					// Llamamos al metodo de la clase viaje que nos permite realizar una reserva y
					// le pasamos los parametros introducidos por el usuario
					Viaje.insertarReserva(destino, codigoCliente, plazasCliente);
					break;
				case 3:
					// Pedimos al usuario que introduzca su codigo de cliente y el codigo de su
					// reserva para poder cancelar dicha reserva
					codigoCliente = UtilidadesSQLite.devolverEntero(sc, "Introduzca el codigo del cliente: ");
					int codigoReserva = UtilidadesSQLite.devolverEntero(sc, "Introduzca el codigo de la reserva: ");
					// Llamamos al metodo de la clase viaje que nos permite cancelar una reserva y
					// le pasamos como parametros los introducidos por el usuario
					Viaje.cancelarReserva(codigoReserva, codigoCliente);
					break;
				case 4:
					// Cambiamos el valor de la variable de salida para que deje de ejecutarse el
					// bucle do/while y mostramos al usuario que esta saliendo
					System.out.println("\nSaliendo ...");
					salir = true;
					break;
				default:
					// En caso del usuario introducir un valor equivocado, le mostramos por pantalla
					// que ha introducido una accion invlaida
					System.out.println("Se ha introducido una accion invalida");
				}

			} while (!salir);
		}
	}

}
