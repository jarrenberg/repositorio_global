package ejercicio;

import java.sql.SQLException;
import java.util.Scanner;

import ejercicio.DAO.ClienteDAO;
import ejercicio.DAO.ReservaDAO;
import ejercicio.DAO.ViajeDAO;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		menu(sc);
	}

	public static void menu(Scanner sc) {
		boolean salir = false;
		do {
			System.out.println("\n\nMENU");
			System.out.println("1) Añadir un viaje");
			System.out.println("2) Modificar un viaje");
			System.out.println("3 Eliminar un viaje");
			System.out.println("4) Añadir un cliente");
			System.out.println("5) Modificar un cliente");
			System.out.println("6 Eliminar un cliente");
			System.out.println("7) Añadir una reserva");
			System.out.println("8) Modificar una reserva");
			System.out.println("9) Eliminar una reserva");
			System.out.println("10) Salir");

			int accion = UtilidadesSQL.devolverEntero(sc, "");
			switch (accion) {
			case 1:
				String destino = UtilidadesSQL.devolverString(sc, "Introduzca el destino del viaje: ");
				int plazasDisponibles = UtilidadesSQL.devolverEntero(sc,
						"Introduzca el numero de plazas disponibles del viaje: ");
				if (ViajeDAO.insertarViaje(new Viaje(destino, plazasDisponibles))) {
					System.out.println("Se ha insertado el viaje");
				} else {
					System.out.println("No se ha insertado el viaje");
				}

				break;
			case 2:
				int codigo = UtilidadesSQL.devolverEntero(sc, "Introduzca el codigo del viaje a modificar: ");
				try {
					Viaje v = ViajeDAO.getViaje(codigo);
					destino = UtilidadesSQL.devolverString(sc, "Introduzca el destino del viaje: ");
					plazasDisponibles = UtilidadesSQL.devolverEntero(sc,
							"Introduzca el numero de plazas disponibles del viaje: ");
					v.setDestino(destino);
					v.setPlazasDisponibles(plazasDisponibles);
					if (ViajeDAO.actualizarViaje(v)) {
						System.out.println("Se ha actualizado el viaje");
					} else {
						System.out.println("No se ha actualizado el viaje");
					}
				} catch (NullPointerException ex) {
					System.out.println(ex.toString());
				} catch (SQLException ex) {
					System.out.println(ex.toString());
				}
				break;
			case 3:
				codigo = UtilidadesSQL.devolverEntero(sc, "Introduzca el codigo del viaje a eliminar: ");
				try {
					Viaje v = ViajeDAO.getViaje(codigo);
					if (ViajeDAO.eliminarViaje(v)) {
						System.out.println("Se ha eliminado el viaje");
					} else {
						System.out.println("No se ha eliminado el viaje");
					}
				} catch (NullPointerException ex) {
					System.out.println(ex.toString());
				}
				break;
			case 4:
				String nombre = UtilidadesSQL.devolverString(sc, "Introduzca el nombre del cliente: ");
				String telefono = UtilidadesSQL.devolverString(sc, "Introduzca el telefono del cliente: ");
				try {
					if (ClienteDAO.insertarCliente(new Cliente(nombre, telefono))) {
						System.out.println("Se ha insertado el cliente");
					} else {
						System.out.println("No se ha podido insertar el cliente");
					}
				} catch (IllegalArgumentException ex) {
					System.out.println(ex.toString());
				}
				break;
			case 5:
				codigo = UtilidadesSQL.devolverEntero(sc, "Introduzca el codigo del cliente a modificar: ");
				Cliente c = ClienteDAO.getCliente(codigo);
				nombre = UtilidadesSQL.devolverString(sc, "Introduzca el nuevo nombre del cliente: ");
				telefono = UtilidadesSQL.devolverString(sc, "Introduzca el nuevo telefono del cliente: ");
				c.setNombre(nombre);
				c.setTelefono(telefono);
				if (ClienteDAO.actualizarCliente(c)) {
					System.out.println("Se ha actualizado al cliente");
				} else {
					System.out.println("No se ha podido actualizar al cliente");
				}
				break;
			case 6:
				codigo = UtilidadesSQL.devolverEntero(sc, "Introduzca el codigo del cliente a eliminar: ");
				try {
					c = ClienteDAO.getCliente(codigo);
					if (ClienteDAO.eliminarCliente(c)) {
						System.out.println("Se ha actualizado al cliente");
					} else {
						System.out.println("No se ha podido actualizar al cliente");
					}
				} catch (NullPointerException ex) {
					System.out.println(ex.toString());
				}
				break;
			case 7:
				int codigoViaje = UtilidadesSQL.devolverEntero(sc,
						"Introduzca el codigo del viaje que le gustaria reservar: ");
				int codigoCliente = UtilidadesSQL.devolverEntero(sc,
						"Introduzca el codigo del cliente que reserva el viaje: ");
				int plazasReservadas = UtilidadesSQL.devolverEntero(sc,
						"Introduzca el numero de plazas que quiere reservar: ");
				Reserva r = null;
				try {
					r = new Reserva(codigoViaje, codigoCliente, plazasReservadas);
					if (ReservaDAO.insertarReserva(r)) {
						System.out.println("Se ha insertado la reserva correctamente");
					} else {
						System.out.println("No se ha podido insertar la reserva");
					}

				} catch (IllegalArgumentException ex) {
					System.out.println(ex.toString());
				}
				break;
			case 8:
				codigo = UtilidadesSQL.devolverEntero(sc,
						"Introduzca el codigo de la reserva que le gustaria modificar: ");
				r = ReservaDAO.getReserva(codigo);
				codigoViaje = UtilidadesSQL.devolverEntero(sc,
						"Introduzca el nuevo codigo del viaje que le gustaria reservar: ");
				codigoCliente = UtilidadesSQL.devolverEntero(sc,
						"Introduzca el nuevo codigo del cliente que reserva el viaje: ");
				plazasReservadas = UtilidadesSQL.devolverEntero(sc,
						"Introduzca el nuevo numero de plazas que quiere reservar: ");
				r.setCodigoViaje(codigoViaje);
				r.setCodigoCliente(codigoCliente);
				r.setPlazasReservadas(plazasReservadas);
				if (ReservaDAO.actualizarReserva(r)) {
					System.out.println("Se ha actualizado la reserva");
				} else {
					System.out.println("No se ha podido actualizar la reserva");
				}
				break;
			case 9:
				codigo = UtilidadesSQL.devolverEntero(sc,
						"Introduzca el codigo de la reserva que le gustaria eliminar: ");
				r = ReservaDAO.getReserva(codigo);
				if (ReservaDAO.cancelarReserva(r)) {
					System.out.println("Se ha cancelado la reserva");
				} else {
					System.out.println("No se ha podido cancelar la reserva");
				}
				break;
			case 10:
				salir = true;
				System.out.println("Saliendo ...");
				break;
			default:
				System.out.println("Has introducido una accion erronea");

			}
		} while (!salir);
	}
}
