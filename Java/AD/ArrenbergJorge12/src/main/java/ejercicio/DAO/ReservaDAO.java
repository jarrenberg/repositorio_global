package ejercicio.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import ejercicio.Reserva;
import ejercicio.UtilidadesSQL;

//APARTADO 4
public class ReservaDAO {

	public static Reserva getReserva(Integer codigo) throws NullPointerException {
		Reserva r = new Reserva(1, 1, 1);
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection con = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = con.prepareStatement("SELECT * FROM reservas where numero_reserva=?")) {
			ps1.setInt(1, codigo);
			ResultSet rs = ps1.executeQuery();
			while (rs.next()) {
				r.setNumeroReserva(rs.getInt(1));
				r.setCodigoViaje(rs.getInt(2));
				r.setCodigoCliente(rs.getInt(3));
				r.setPlazasReservadas(rs.getInt(4));
				r.setEstado(rs.getString(5));
			}
			if ((r.getCodigoCliente() == 1) && (r.getCodigoViaje() == 1) && (r.getPlazasReservadas() == 1)) {
				throw new NullPointerException("No existe una reserva con dicho codigo");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return r;
	}

	public static boolean insertarReserva(Reserva r) {
		boolean insertado = false;
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();

		try (Connection con = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = con.prepareStatement("SELECT plazas_disponibles FROM viajes WHERE codigo=?");
				PreparedStatement ps2 = con.prepareStatement("UPDATE viajes SET plazas_disponibles=? WHERE codigo=?");
				PreparedStatement ps3 = con.prepareStatement(
						"INSERT INTO reservas (codigo_viaje, codigo_cliente, plazas_reservadas, estado) VALUES (?,?,?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS);
				PreparedStatement ps4 = con.prepareStatement(
						"SELECT numero_reserva FROM reservas WHERE codigo_viaje=? AND codigo_cliente=? AND plazas_reservadas=?")) {
			// 1) Obtener plazas disponibles para el viaje
			ps1.setInt(1, r.getCodigoViaje());
			ResultSet rs1 = ps1.executeQuery();
			int plazasDisponibles = -1;
			if (rs1.next()) {
				plazasDisponibles = rs1.getInt(1);
			}

			// 2) Verificar que haya suficientes plazas
			if (plazasDisponibles - r.getPlazasReservadas() >= 0) {
				r.setEstado("A");
				// 3) Insertar reserva
				ps3.setInt(1, r.getCodigoViaje());
				ps3.setInt(2, r.getCodigoCliente());
				ps3.setInt(3, r.getPlazasReservadas());
				ps3.setString(4, r.getEstado()); // asumiendo que "A" es reservado/activo
				int filasInsertadas = ps3.executeUpdate();

				if (filasInsertadas > 0) {
					insertado = true;

					// 4) Actualizar plazas disponibles
					int plazasActualizadas = plazasDisponibles - r.getPlazasReservadas();
					ps2.setInt(1, plazasActualizadas);
					ps2.setInt(2, r.getCodigoViaje());
					ps2.executeUpdate();

					// 5) Obtener numero_reserva generado (si la BD lo genera autoincrement)
					ps4.setInt(1, r.getCodigoViaje());
					ps4.setInt(2, r.getCodigoCliente());
					ps4.setInt(3, r.getPlazasReservadas());
					ResultSet rs3 = ps4.executeQuery();
					if (rs3.next()) {
						r.setNumeroReserva(rs3.getInt(1));
					}
				}

			} else {
				r.setEstado("E");
				ps3.setInt(1, r.getCodigoViaje());
				ps3.setInt(2, r.getCodigoCliente());
				ps3.setInt(3, r.getPlazasReservadas());
				ps3.setString(4, r.getEstado());
				int filasInsertadas = ps3.executeUpdate();
				if (filasInsertadas > 0) {
					insertado = true;
				}
				// Obtener numero_reserva generado (si la BD lo genera autoincrement)
				ps4.setInt(1, r.getCodigoViaje());
				ps4.setInt(2, r.getCodigoCliente());
				ps4.setInt(3, r.getPlazasReservadas());
				ResultSet rs3 = ps4.executeQuery();
				if (rs3.next()) {
					r.setNumeroReserva(rs3.getInt(1));
				}

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return insertado;
	}

	// APARTADO 7

	public static boolean cancelarReserva(Reserva r) {
		boolean cancelado = false;
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection con = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				CallableStatement cs = con.prepareCall("CALL cancelar_reserva(?, ?, ?, ?, ?, ?)")) {

			cs.setInt(1, r.getNumeroReserva());
			cs.setInt(2, r.getCodigoViaje());
			cs.setInt(3, r.getCodigoCliente());
			cs.setInt(4, r.getPlazasReservadas());
			cs.setString(5, r.getEstado());
			cs.registerOutParameter(6, java.sql.Types.BOOLEAN);

			cs.execute();

			cancelado = cs.getBoolean(6);
			r.setEstado("C");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return cancelado;
		}
		return cancelado;
	}

	public static boolean actualizarReserva(Reserva r) {
		boolean actualizado = false;
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		Connection con = null;
		try (PreparedStatement ps1 = con.prepareStatement(
				"UPDATE reservas set codigo_viaje=?, codigo_cliente=?,plazas_reservadas=? where numero_reserva=?")) {
			con = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
			// Desactivar autocommit para controlar la transacción
			con.setAutoCommit(false);

			ps1.setInt(1, r.getCodigoViaje());
			ps1.setInt(2, r.getCodigoCliente());
			ps1.setInt(3, r.getPlazasReservadas());
			ps1.setInt(4, r.getNumeroReserva());
			int rs = ps1.executeUpdate();
			if (rs > 0) {
				actualizado = true;

			}

			// Confirmar la transacción si todo fue correcto
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// En caso de error, intentar deshacer cambios
			try {
				if (con != null && !con.isClosed()) {
					con.rollback();
					con.setAutoCommit(true);
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.setAutoCommit(true);
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return actualizado;
	}

}
