package ejercicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Viaje {

	// Atributos de la clase viaje
	private Integer codigo;
	private String destino;
	private Integer plazasDisponibles;

	// Constructor con todos los parametros de la clase viaje
	public Viaje(Integer codigo, String destino, Integer plazasDisponibles) {
		this.codigo = codigo;
		this.destino = destino;
		this.plazasDisponibles = plazasDisponibles;
	}

	// Metodo que devuelve el numero de plazas libres en el destino del viaje pasado
	// como parametro
	public static int plazasLibres(String destino) {
		// Variable que guardara las plazas libres en el viaje con el destino pasado
		// como parametro. En caso de ser negativo indica que el destino introducido
		// como parametro no existe
		int plazasLibres = -1;
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:viajesbus.db");
				PreparedStatement ps = con
						.prepareStatement("SELECT plazas_disponibles from viajes where destino = ?")) {
			ps.setString(1, destino);
			ResultSet rs = ps.executeQuery();
			// En caso de ejecutar la consulta y que exista dicho viaje, guardamos el numero
			// de plazas libres en la variable
			while (rs.next()) {
				plazasLibres = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Devolvemos el numero de plazas libres
		return plazasLibres;
	}

	// metodo que permite insertar una reserva pasandole como parametro el destino,
	// el codigo de cliente y las plazas que desea reservar el cliente
	public static void insertarReserva(String destino, int codigoCliente, int plazasCliente) {
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:viajesbus.db");
				PreparedStatement psComprobacionDestino = con
						.prepareStatement("SELECT codigo from viajes where destino=?");
				PreparedStatement psComprobacionPlazas = con
						.prepareStatement("SELECT plazas_disponibles from viajes where destino=?");
				PreparedStatement psInsercionPlazasValidas = con
						.prepareStatement("INSERT into reservas values (NULL,?,?,?,'A')");
				PreparedStatement psInsercionPlazasInvalidas = con
						.prepareStatement("INSERT into reservas values (NULL,?,?,?,'E')");
				PreparedStatement psActualizacioinPlazasDisponibles = con
						.prepareStatement("UPDATE viajes SET plazas_disponibles=? where codigo=?")) {

			// Lo primero es comprobar que el destino existe. Esto lo hacemos utilizando una
			// variable (codigoViaje).
			// Si existe dicho destino, la variable cambiara su valor y si se queda igual
			// significa que no existe dicho viaje
			psComprobacionDestino.setString(1, destino);
			int codigoViaje = 0;
			ResultSet rs1 = psComprobacionDestino.executeQuery();
			while (rs1.next()) {
				codigoViaje = rs1.getInt(1);
			}

			// En caso de que el viaje exista ...
			if (codigoViaje != 0) {
				// Guardamos las plazas disponibles del viaje requerido por el usuario
				psComprobacionPlazas.setString(1, destino);
				int plazasDisponibles = 0;
				ResultSet rs2 = psComprobacionPlazas.executeQuery();
				while (rs2.next()) {
					plazasDisponibles = rs2.getInt(1);
				}
				// En el caso en el que las plazas introducidas por el usuario sean menores o
				// igual al numero de plazas disponibles,acutalizamos las plazas disponibles en
				// el viaje y creamos una reserva con estado 'A'
				if (plazasCliente <= plazasDisponibles) {
					psInsercionPlazasValidas.setInt(1, codigoViaje);
					psInsercionPlazasValidas.setInt(2, codigoCliente);
					psInsercionPlazasValidas.setInt(3, plazasCliente);
					psActualizacioinPlazasDisponibles.setInt(1, (plazasDisponibles - plazasCliente));
					psActualizacioinPlazasDisponibles.setInt(2, codigoViaje);
					psInsercionPlazasValidas.executeUpdate();
					psActualizacioinPlazasDisponibles.executeUpdate();
					System.out.println("Se ha añadido su reserva como adjudicada");

					// En el caso en el que las plazas introducidas por el usuario sean mayores al
					// numero de plazas disponibles,creamos la reserva con el estado 'E'
				} else {
					psInsercionPlazasInvalidas.setInt(1, codigoViaje);
					psInsercionPlazasInvalidas.setInt(2, codigoCliente);
					psInsercionPlazasInvalidas.setInt(3, plazasCliente);
					psInsercionPlazasInvalidas.executeUpdate();
					System.out.println("Se ha añadido su reserva como en espera");
				}

			} else {
				// Codigo que se ejecuta si no existe el destino pasado como parametro
				System.out.println("No existe dicho destino, por lo que no podemos realizar una reserva");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Metodo que permite cancelar una reserva si existen los parametros en la tabla
	public static void cancelarReserva(int codigoReserva, int codigoCliente) {
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:viajesbus.db");
				PreparedStatement psComprobacionClienteExiste = con
						.prepareStatement("SELECT numero_reserva from reservas where codigo_cliente=? and estado='A'");

				PreparedStatement psActualizacionReserva = con
						.prepareStatement("UPDATE reservas SET estado = 'C' where numero_reserva = ?");

				PreparedStatement psPlazasReservadas = con
						.prepareStatement("SELECT plazas_reservadas from reservas where codigo_cliente=?");

				PreparedStatement psPlazasDisponibles = con.prepareStatement(
						"SELECT plazas_disponibles from viajes where codigo = (SELECT codigo_viaje from reservas where numero_reserva = ?) ");

				PreparedStatement psActualizacionPlazasDisponibles = con.prepareStatement(
						"UPDATE viajes SET plazas_disponibles = ? where codigo = (SELECT codigo_viaje from reservas where numero_reserva=?)")) {

			// Antes de eliminar la reserva, comprobamos que existe el cliente con los datos
			// pasados
			// como parametros, usando la variable numeroQuery como control
			psComprobacionClienteExiste.setInt(1, codigoCliente);
			int numeroQuery = -1;
			ResultSet rs1 = psComprobacionClienteExiste.executeQuery();
			while (rs1.next()) {
				numeroQuery = rs1.getInt(1);
				System.out.println(numeroQuery);
			}
			// En el caso de que exista la reserva ...
			if (numeroQuery == codigoReserva) {
				psActualizacionReserva.setInt(1, numeroQuery);
				// Guardamos en una variable el numero de plazas reservadas en la reserva
				psPlazasReservadas.setInt(1, codigoCliente);
				int plazasReservadas = 0;
				ResultSet rs2 = psPlazasReservadas.executeQuery();
				while (rs2.next()) {
					plazasReservadas = rs2.getInt(1);
				}
				// Guardamos el numero de plazas disponibles en el viaje del cual esta hecha la
				// reserva
				psPlazasDisponibles.setInt(1, codigoReserva);
				int plazasDisponibles = 0;
				ResultSet rs3 = psPlazasDisponibles.executeQuery();
				while (rs3.next()) {
					plazasDisponibles = rs3.getInt(1);
				}
				// Actualizamos la tabla de reservas cambiando el estado a 'C' y devolvemos las plazas reservadas a las disponibles
				psActualizacionPlazasDisponibles.setInt(1, (plazasReservadas + plazasDisponibles));
				psActualizacionPlazasDisponibles.setInt(2, codigoReserva);
				psActualizacionReserva.executeUpdate();
				psActualizacionPlazasDisponibles.executeUpdate();
				System.out.println("Se ha cancelado la reserva");
			} else { // En el caso que no exista una reserva con los parametros, mostramos al usuario
						// que no puede cancelar una reserva que no exista
				System.out.printf(
						"No se puede cancelar la reserva porque no existe una reserva con el codigo (%d) y el codigo de cliente (%d) o porque dicha reserva esta en espera\n",
						codigoReserva, codigoCliente);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Integer getPlazasDisponibles() {
		return plazasDisponibles;
	}

	public void setPlazasDisponibles(Integer plazasDisponibles) {
		this.plazasDisponibles = plazasDisponibles;
	}

}
