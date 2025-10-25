package ArrenbergJorge.ejercicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SQLTransacciones {
	public static boolean addArticulo(int id, String nombre, int existencias, double precio) {
		boolean anyadido = false;
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection conn = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = conn.prepareStatement("INSERT INTO articulo values(?,?,?,?)");) {
			ps1.setInt(1, id);
			ps1.setString(2, nombre);
			ps1.setInt(3, existencias);
			ps1.setDouble(4, precio);
			ps1.executeUpdate();
			anyadido = true;
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return anyadido;
	}

	public static boolean addVenta(int id_articulo, int cantidad, LocalDate fecha) {
		boolean ventaRealizada = false;
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection conn = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = conn.prepareStatement("INSERT INTO ventas values(NULL,?,?,?)");
				PreparedStatement ps2 = conn.prepareStatement(
						"update articulo set existencias= (select existencias from articulo where id=?)-(select cantidad from ventas where id_articulo=? order by id desc limit 1) where id=?;")) {
			conn.setAutoCommit(false);
			if ((numArticulosStock(id_articulo) - cantidad) >= 0) {
				ps1.setInt(1, id_articulo);
				ps1.setInt(2, cantidad);
				ps1.setString(3, fecha.toString());
				ps2.setInt(1, id_articulo);
				ps2.setInt(2, id_articulo);
				ps2.setInt(3, id_articulo);
				ps1.executeUpdate();
				ps2.executeUpdate();
				conn.commit();
				ventaRealizada = true;
				System.out.println("Transaccion completada");
			} else {
				System.out.println("No se ha podido completar la transaccion");
				conn.rollback();
				System.out.println("Transacción revertida debido a un error.");
				conn.setAutoCommit(true);
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return ventaRealizada;
	}

	public static void mostrarArticulos() {
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection conn = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = conn.prepareStatement("SELECT * from articulo")) {
			ResultSet rs = ps1.executeQuery();
			System.out.println(" --- TABLA DE ARTICULOS ---");
			while (rs.next()) {
				System.out.printf("\nID=%d\tNombre=%s\tExistencias=%d\tPrecio=%.2f€\n", rs.getInt(1), rs.getString(2),
						rs.getInt(3), rs.getDouble(4));
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	public static void mostrarVentas() {
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection conn = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = conn.prepareStatement("SELECT * from ventas")) {
			ResultSet rs = ps1.executeQuery();
			System.out.println(" --- TABLA DE VENTAS ---\n");
			while (rs.next()) {
				System.out.printf("\nID=%d\tID Articulo=%d\tCantidad=%d\tFecha=%s\n", rs.getInt(1), rs.getInt(2),
						rs.getInt(3), rs.getDate(4).toString());
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	public static int numArticulosStock(int id_articulo) {
		int res = 0;
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection conn = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = conn.prepareStatement("SELECT existencias from articulo where id=?")) {
			ps1.setInt(1, id_articulo);
			ResultSet rs = ps1.executeQuery();
			while (rs.next()) {
				res = res + rs.getInt(1);
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return res;
	}
}
