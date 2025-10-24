package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;

// Usamos las siguientes dos lineas para conocer el numero de columnas que tiene
// la tabla
/*
 * ResultSetMetaData rsmd = rs.getMetaData();
 */

public class Main {
	public static void main(String[] args) throws SQLTimeoutException {
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

		Scanner sc = new Scanner(System.in);
		boolean salir = false;
		do {
			System.out.println("\nMENU:\n");
			System.out.println("1. Mostrar la lista de clientes");
			System.out.println("2. Añadir cliente");
			System.out.println("3. Eliminar cliente");
			System.out.println("4. Salir");

			int accion = devolverAccion(sc);

			switch (accion) {
			case 1:
				mostrarTabla(url, user, pswd);
				break;
			case 2:
				String nombre = devolverString(sc, "Introduzca el nombre de la persona a añadir:");
				String email = devolverString(sc, "Introduzca el email de la persona a añadir:");
				int saldo = devolverEntero(sc, "Introduzca el saldo de la persona a añadir:");
				LocalDate fechaAlta = devolverFecha(sc, "Introduzca la fecha de alta de la persona a añadir:");
				LocalDate fechaCreacion = devolverFecha(sc, "Introduzca la fecha de creacion de la persona a añadir:");
				LocalDate fechaActualizacion = devolverFecha(sc,
						"Introduzca la fecha de actualizacion de la persona a añadir:");
				int r = addPersona(url, user, pswd, nombre, email, saldo, fechaAlta.toString(),
						fechaCreacion.toString(), fechaActualizacion.toString());
				if (r > 0) {
					System.out.println("Se ha actualizado la BBDD");
				} else {
					System.out.println("No se ha podido añadir al cliente");
				}
				break;
			case 3:
				String nombre1 = devolverString(sc, "Introduzca el nombre de la persona a eliminar:");
				String email1 = devolverString(sc, "Introduzca el email de la persona a eliminar:");
				int r2 = removePersona(url, user, pswd, nombre1, email1);
				if (r2 > 0) {
					System.out.println("Se ha actualizado la BBDD");
				} else {
					System.out.println("No se ha podido eliminar al cliente");
				}
				break;
			case 4:
				System.out.println("Saliendo...");
				salir = true;
				break;
			}
		} while (!salir);
		sc.close();
	}

	public static int devolverAccion(Scanner sc) {
		int accion = 0;
		boolean valido = false;
		do {
			try {
				accion = sc.nextInt();
				sc.nextLine();
				if (accion < 1 || accion > 4) {
					System.out.println("Se debe introducir una accion valida (1-4)");
				} else {
					valido = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("Se debe introducir un numero entero");
				sc.nextLine();
			} catch (NoSuchElementException e) {
				System.out.println(e.getMessage());
			} catch (IllegalStateException e) {
				System.out.println(e.getMessage());
			}
		} while (!valido);
		return accion;
	}

	public static int addPersona(String url, String user, String pswd, String nombre, String email, int saldo,
			String fechaAlta, String fechaCreacion, String fechaActualizacion) {
		int rs = 0;
		try (Connection con = DriverManager.getConnection(url, user, pswd); Statement stmt = con.createStatement();) {
			String sql = String.format("INSERT INTO cliente VALUES (null, '%s', '%s', %d, '%s', '%s', '%s')", nombre,
					email, saldo, fechaAlta, fechaCreacion, fechaActualizacion);
			rs = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.printf("Error(No se ha podido insertar al sujeto): %s.\n", e.getMessage());
		}
		return rs;

	}

	public static int removePersona(String url, String user, String pswd, String nombre, String email) {
		int rs = 0;
		try (Connection con = DriverManager.getConnection(url, user, pswd); Statement stmt = con.createStatement()) {
			String sql = String.format("DELETE FROM cliente where nombre like '%s' and email like '%s'", nombre, email);
			rs = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}

	public static void mostrarTabla(String url, String user, String pswd) {
		String resultado = "";
		try (ResultSet rs2 = DriverManager.getConnection(url, user, pswd).createStatement()
				.executeQuery("SELECT * FROM cliente")) {
			try {
				while (rs2.next()) {
					System.out.printf("codigo:%s\t", rs2.getString("codigo"));
					System.out.printf("nombre:%s\t", rs2.getString("nombre"));
					System.out.printf("email:%s\t", rs2.getString("email"));
					System.out.printf("saldo:%s\t", rs2.getString("saldo"));
					System.out.printf("fecha_alta:%s\t", rs2.getString("fecha_alta"));
					System.out.printf("fecha_creacion:%s\t", rs2.getString("fecha_creacion"));
					System.out.printf("fecha_actualizacion:%s\n", rs2.getString("fecha_actualizacion"));
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}

		} catch (Exception ex) {
			System.out.println(ex);
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
