package ejercicio.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import ejercicio.Reserva;
import ejercicio.UtilidadesSQL;

public class ReservaDAO {
	// metodo que permite insertar una reserva pasandole como parametro el destino,
	// el codigo de cliente y las plazas que desea reservar el cliente
	public static boolean insertarReserva(Reserva r) {
		boolean insertado = false;
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection con = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = con.prepareStatement("INSERT INTO reservas values (?,?,?,?,?)");
				PreparedStatement ps2 = con.prepareStatement("SELECT plazas_disponibles from viajes where codigo=?");
				PreparedStatement ps3 = con.prepareStatement("UPDATE viajes SET plazas_disponibles=? where codigo=?")) {

			ps2.setInt(1, r.getCodigoViaje());
			ResultSet rs = ps2.executeQuery();
			int numPlazas = -1;
			while (rs.next()) {
				numPlazas = rs.getInt(1);
			}

			ps1.setNull(1, Types.INTEGER);
			ps1.setInt(2, r.getCodigoViaje());
			ps1.setInt(3, r.getCodigoCliente());
			ps1.setInt(4, r.getPlazasReservadas());

			if ((numPlazas - r.getPlazasReservadas()) >= 0) {
				ps1.setString(5, "A");
				ps3.setInt(1, (numPlazas - r.getPlazasReservadas()));
				ps3.setInt(2, r.getCodigoViaje());
				ps3.executeUpdate();

			} else {
				ps1.setString(6, "E");
			}
			int rs1 = ps1.executeUpdate();
			if (rs1 > 0) {
				insertado = true;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return insertado;
	}

	// Metodo que permite cancelar una reserva si existen los parametros en la tabla
	public static void cancelarReserva(Reserva r) {
		boolean insertado = false;
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection con = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = con.prepareStatement("DELETE from reservas where ")) {
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
