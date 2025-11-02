package ejercicio;

import java.awt.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;

public class UtilidadesSQLite {

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

	public static int devolverEntero(Scanner sc, String mensaje) {
		int resultado = 0;
		boolean valido = false;
		do {
			try {
				System.out.print(mensaje);
				resultado = sc.nextInt();
				valido = true;
			} catch (InputMismatchException e) {
				System.out.println("Se debe introducir un numero valido");
				sc.nextLine();
			} catch (NoSuchElementException e) {
				System.out.println(e.getMessage());
			} catch (IllegalStateException e) {
				System.out.println(e.getMessage());
			}
		} while (!valido);
		sc.nextLine();
		return resultado;
	}

	public static double devolverDouble(Scanner sc, String mensaje) {
		double resultado = 0;
		boolean valido = false;
		do {
			try {
				System.out.print(mensaje);
				resultado = sc.nextDouble();
				valido = true;
			} catch (InputMismatchException e) {
				System.out.println("Se debe introducir un numero valido");
				sc.nextLine();
			} catch (NoSuchElementException e) {
				System.out.println(e.getMessage());
			} catch (IllegalStateException e) {
				System.out.println(e.getMessage());
			}
		} while (!valido);
		sc.nextLine();
		return resultado;
	}

	public static LocalDate devolverFecha(Scanner sc, String mensaje) {
		LocalDate f = null;
		while (f == null) {
			try {
				System.out.println(mensaje);
				String fecha = sc.nextLine();
				try {
					f = LocalDate.parse(fecha);
				} catch (DateTimeParseException ex) {
					f = null;
					System.out.println(ex.getMessage());
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return f;
	}

	public static boolean existeEntryParamInt(int param, String campo, String nombreTabla) {
		boolean existe = false;
		try (Connection conn = DriverManager
				.getConnection("jdbc:sqlite:C:\\DAM2\\Java\\AD\\ArrenbergJorge11\\mantenimiento.db");
				PreparedStatement ps1 = conn
						.prepareStatement(String.format("SELECT * from %s where %s=?", nombreTabla, campo))) {
			ps1.setInt(1, param);
			ResultSet rs = ps1.executeQuery();
			while (rs.next()) {
				existe = true;
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return existe;
	}

	public static boolean existeEntryParamString(String param, String campo, String nombreTabla) {
		boolean existe = false;
		try (Connection conn = DriverManager
				.getConnection("jdbc:sqlite:C:\\DAM2\\Java\\AD\\ArrenbergJorge11\\mantenimiento.db");
				PreparedStatement ps1 = conn
						.prepareStatement(String.format("SELECT * from %s where %s=?", nombreTabla, campo))) {
			ps1.setString(1, param);
			ResultSet rs = ps1.executeQuery();
			while (rs.next()) {
				existe = true;
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return existe;
	}

	public static void mostrarTablas(String baseDeDatos, String conexion) {
		try (Connection conn = DriverManager.getConnection(conexion);
				PreparedStatement ps1 = conn.prepareStatement("SELECT name FROM sqlite_master WHERE type='table'")) {

			ResultSet rs = ps1.executeQuery();
			System.out.printf("\n***  TABLAS DE %s  ***\n", baseDeDatos);

			while (rs.next()) {
				System.out.println(" - " + rs.getString(1));
			}

		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	public static void mostrarTodosRegistrosTabla(String tabla, String conexion) {
		try (Connection conn = DriverManager.getConnection(conexion); Statement st = conn.createStatement()) {

			ResultSet rs = st.executeQuery("SELECT * FROM " + tabla);
			ResultSetMetaData rsmd = rs.getMetaData();

			System.out.printf("\n***  TABLA %s  ***\n", tabla);

			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				System.out.printf("| %-20s ", rsmd.getColumnName(i));
			}
			System.out.println("|");

			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				System.out.printf("|%-22s", "----------------------");
			}
			System.out.println("|");

			while (rs.next()) {
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					System.out.printf("| %-20s ", rs.getString(i));
				}
				System.out.println("|");
			}

		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	public static boolean anyadirABaseDeDatos(String tabla, String conexion, ArrayList<Object> parametros) {
		boolean insertado = false;
		try (Connection conn = DriverManager.getConnection(conexion); Statement st = conn.createStatement()) {
			String valores = "(";
			for (Object object : parametros) {
				valores = String.format("%s'%s',", valores, object.toString());
			}
			valores = valores.substring(0, valores.length() - 1);
			String sentencia = String.format("insert into %s values %s)", tabla, valores);

			System.out.println(sentencia);
			int rs = st.executeUpdate(sentencia);
			if (rs > 0) {
				insertado = true;
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return insertado;
	}

	public static boolean modificarRegistroBaseDeDatos(String tabla, String conexion, String nombreCampoPK, String pk,
			String campoMod, String valorNuevo) {
		boolean modificado = false;
		try (Connection conn = DriverManager.getConnection(conexion);
				PreparedStatement ps = conn.prepareStatement(
						String.format("UPDATE %s SET %s=? WHERE %s=?", tabla, campoMod, nombreCampoPK))) {
			ps.setString(1, valorNuevo);
			ps.setString(2, pk);
			int rs = ps.executeUpdate();
			if (rs > 0) {
				modificado = true;
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return modificado;
	}

	public static boolean eliminarRegistroBaseDeDatos(String tabla, String conexion, String nombreCampo, String valor,
			String nombreCampoPK) {
		boolean eliminado = false;
		try (Connection conn = DriverManager.getConnection(conexion); Statement st = conn.createStatement()) {
			String sql = String.format("DELETE from %s WHERE %s=(SELECT %s from %s where %s='%s')", tabla,
					nombreCampoPK, nombreCampoPK, tabla, nombreCampo, valor);
			System.out.println(sql);
			int rs = st.executeUpdate(sql);
			if (rs > 0) {
				eliminado = true;
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return eliminado;
	}

	public static boolean eliminarRegistroBaseDeDatos(String tabla, String tablaDependiente, String conexion,
			String nombreCampo, String valor, String nombreCampoPK, String nombreCampoFK) {
		boolean eliminado = false;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(conexion);
			conn.setAutoCommit(false);
			try (PreparedStatement ps = conn
					.prepareStatement(String.format("DELETE from %s where %s=(SELECT %s from %s where %s=?)",
							tablaDependiente, nombreCampoFK, nombreCampoPK, tabla, nombreCampo));
					PreparedStatement ps2 = conn
							.prepareStatement(String.format("DELETE from %s WHERE %s=?", tabla, nombreCampo))) {
				ps.setString(1, valor);
				int resultado = ps.executeUpdate();
				if (resultado > 0) {
					ps2.setString(1, valor);
					int resultado2 = ps2.executeUpdate();
					if (resultado2 > 0) {
						conn.commit();
						eliminado = true;
					} else {
						conn.rollback();
					}
				} else {
					conn.rollback();
				}
			}

		} catch (SQLException ex) {
			System.out.printf("Error SQL: %s\n", ex.toString());
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e) {
					System.out.printf("Error al hacer rollback: %s\n", e.toString());
				}
			}
		} catch (Exception ex) {
			System.out.printf("Error general: %s\n", ex.toString());
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e) {
					System.out.printf("Error al hacer rollback: %s\n", e.toString());
				}
			}
		} finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (SQLException ex) {
					System.out.printf("Error al cerrar conexión: %s\n", ex.toString());
				}
			}
		}
		return eliminado;
	}

}