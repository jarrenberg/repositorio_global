package ejercicio.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import ejercicio.UtilidadesSQL;
import ejercicio.Viaje;

//APARTADO 4
public class ViajeDAO {

	public static Viaje getViaje(Integer codigoViaje) throws NullPointerException {
		Viaje v = new Viaje("", -1);
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection con = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = con.prepareStatement("SELECT * from viajes where codigo=?")) {
			ps1.setInt(1, codigoViaje);
			ResultSet rs = ps1.executeQuery();
			while (rs.next()) {
				v.setCodigo(rs.getInt(1));
				v.setDestino(rs.getString(2));
				v.setPlazasDisponibles(rs.getInt(3));
			}
			if ((v.getDestino() == "") && (v.getPlazasDisponibles() == -1)) {
				throw new NullPointerException("No existe un viaje con dicho codigo");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return v;
	}
	
	//APARTADO 6
	
	public static int plazasLibres(String destino) {
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		int plazasLibres = -1;
		try (Connection con = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps = con
						.prepareStatement("SELECT plazas_disponibles from viajes where destino = ?")) {

			ps.setString(1, destino);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				plazasLibres = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return plazasLibres;
	}


	public static boolean insertarViaje(Viaje v) {
		boolean insertado = false;
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection con = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = con.prepareStatement("INSERT INTO viajes values (?,?,?)");
				PreparedStatement ps2 = con
						.prepareStatement("SELECT codigo from viajes where destino = ? and plazas_disponibles = ?")) {
			ps1.setNull(1, Types.INTEGER);
			ps1.setString(2, v.getDestino());
			ps1.setInt(3, v.getPlazasDisponibles());
			ps2.setString(1, v.getDestino());
			ps2.setInt(2, v.getPlazasDisponibles());

			int rs = ps1.executeUpdate();
			if (rs > 0) {
				insertado = true;
			}

			ResultSet rs1 = ps2.executeQuery();
			Integer codigo = -1;
			while (rs1.next()) {
				codigo = rs1.getInt(1);
			}
			v.setCodigo(codigo);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return insertado;
	}

	public static boolean eliminarViaje(Viaje v) {
		boolean eliminado = false;
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection con = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = con
						.prepareStatement("DELETE from viajes where codigo=?")) {
			ps1.setInt(1, v.getCodigo());
			int rs = ps1.executeUpdate();
			if (rs > 0) {
				eliminado = true;
			}
			v = null;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return eliminado;
	}

	public static boolean actualizarViaje(Viaje v) throws SQLException {
		boolean actualizado = false;
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection con = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = con
						.prepareStatement("UPDATE viajes SET destino = ?, plazas_disponibles=? where codigo = ?")) {
			ps1.setString(1, v.getDestino());
			ps1.setInt(2, v.getPlazasDisponibles());
			ps1.setInt(3, v.getCodigo());
			int rs = ps1.executeUpdate();
			if (rs > 0) {
				actualizado = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return actualizado;
	}
}
