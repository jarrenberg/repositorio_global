package ArrenbergJorge.ejercicio;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		addVenta(2, 4, LocalDate.now());
	}

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

	public static void addVenta(int id_articulo, int cantidad, LocalDate fecha) {
		int rs = 0;
		String[] credenciales = credencialesConexionSQL();
		try (Connection conn = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = conn.prepareStatement("INSERT INTO ventas values(NULL,?,?,?)");
				PreparedStatement ps2 = conn.prepareStatement(
						"update articulo set existencias= (select existencias from articulo where id=?)-(select cantidad from ventas where id_articulo=? order by id desc limit 1);")) {
			conn.setAutoCommit(false);
			ps1.setInt(1, id_articulo);
			ps1.setInt(2, cantidad);
			ps1.setString(3, fecha.toString());
			ps2.setInt(1, id_articulo);
			ps2.setInt(2, id_articulo);
			ps1.executeUpdate();
			System.out.println("Exito");
			ps2.executeUpdate();
			conn.commit();

		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		} finally {

		}
	}

	public static String devolverString(Scanner sc, String mensaje) {
		String resultado = null;
		boolean valido = false;
		do {
			try {
				System.out.println(mensaje);
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
				System.out.println(mensaje);
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
				System.out.println(mensaje);
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

}
