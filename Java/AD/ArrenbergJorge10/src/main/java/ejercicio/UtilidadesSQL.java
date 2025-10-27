package ejercicio;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;

public class UtilidadesSQL {

	public static String[] credencialesConexionSQL() {
		Properties cfg = new Properties();
		try {
			cfg.load(new FileInputStream("mysqld.cnf"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		String server = cfg.getProperty("server");
		String port = cfg.getProperty("port");
		String db = cfg.getProperty("nombrebd");

		String url = String.format("jdbc:mysql://%s:%s/%s", server, port, db);
		String user = cfg.getProperty("user");
		String pswd = cfg.getProperty("password");
		String[] resultado = { url, user, pswd };
		return resultado;
	}

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
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		boolean existe = false;
		try (Connection conn = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
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
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		boolean existe = false;
		try (Connection conn = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
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

	public static void mostrarBasesDeDatos() {
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection conn = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = conn.prepareStatement("SHOW DATABASES;")) {
			ResultSet rs = ps1.executeQuery();
			System.out.printf("\n***  BASES DE DATOS  ***\n");
			while (rs.next()) {
				System.out.println(" - " + rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	public static void mostrarTablas(String baseDeDatos) {
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection conn = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = conn.prepareStatement("SHOW TABLES;")) {
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

	public static void mostrarTodosRegistrosTabla(String tabla) {
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection conn = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = conn.prepareStatement(String.format("SELECT * FROM %s", tabla))) {
			ResultSet rs = ps1.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			System.out.printf("\n***  TABLA %s  ***\n", tabla);
			while (rs.next()) {
				int i = 1;
				while (i <= rsmd.getColumnCount()) {
					System.out.printf(" - %s\t", rs.getString(i));
					i++;
				}
				System.out.println(" ");
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

}