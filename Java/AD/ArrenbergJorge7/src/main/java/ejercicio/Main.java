package ejercicio;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	private static final int accion_max = 9;

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
			System.out.println("3. Mostrar cliente");
			System.out.println("4. Actualizar cliente");
			System.out.println("5. Eliminar cliente");
			System.out.println("6. Mostrar todas las ordenes");
			System.out.println("7. Añadir la orden de un cliente");
			System.out.println("8. Cerrar incidencia");
			System.out.println("9. Salir");

			int accion = devolverAccion(sc);

			switch (accion) {
				case 1:
					selectAllClientes(url, user, pswd);
					break;
				case 2:
					String nombre = devolverString(sc, "Introduzca el nombre de la persona a añadir:");
					String email = devolverString(sc, "Introduzca el email de la persona a añadir:");
					int saldo = devolverEntero(sc, "Introduzca el saldo de la persona a añadir:");
					LocalDate fechaAlta = devolverFecha(sc, "Introduzca la fecha de alta de la persona a añadir:");
					LocalDate fechaCreacion = devolverFecha(sc,
							"Introduzca la fecha de creacion de la persona a añadir:");
					LocalDate fechaActualizacion = devolverFecha(sc,
							"Introduzca la fecha de actualizacion de la persona a añadir:");
					int r = addCliente(url, user, pswd, nombre, email, saldo, fechaAlta.toString(),
							fechaCreacion.toString(), fechaActualizacion.toString());
					if (r > 0) {
						System.out.println("Se ha actualizado la BBDD");
					} else {
						System.out.println("No se ha podido añadir al cliente");
					}
					break;
				case 3:
					nombre = devolverString(sc,
							"Introduzca el nombre de la persona de la que quiere mostrar la informacion:");
					email = devolverString(sc,
							"Introduzca el email de la persona de la que quiere mostrar la informacion:");
					String persona = selectCliente(url, user, pswd, nombre, email);
					System.out.println(persona);
					break;
				case 4:
					nombre = devolverString(sc,
							"Introduzca el nombre de la persona de la que quiere actualizar la informacion:");
					email = devolverString(sc,
							"Introduzca el email de la persona de la que quiere actualizar la informacion:");
					String campo = devolverString(sc, "Introduzca el campo a modificar:");
					String campoMod = devolverString(sc, "Introduzca el nuevo valor del campo a modificar:");
					int filas = updateCliente(url, user, pswd, nombre, email, campo, campoMod);
					if (filas > 0) {

						System.out.println("Se ha actualizado la tabla con exito");
					} else {
						System.out.println("No se ha actualizado la tabla");
					}

					break;
				case 5:
					nombre = devolverString(sc, "Introduzca el nombre de la persona a eliminar:");
					email = devolverString(sc, "Introduzca el email de la persona a eliminar:");
					int r2 = removeCliente(url, user, pswd, nombre, email);
					if (r2 > 0) {
						System.out.println("Se ha actualizado la BBDD");
					} else {
						System.out.println("No se ha podido eliminar al cliente");
					}
					break;

				case 6:
					selectAllOrdenes(url, user, pswd);
					break;
				case 7:
					int codigoCliente = devolverEntero(sc, "Introduzca el codigo del cliente: ");
					try {
						if (añadirOrdenCliente(url, user, pswd, codigoCliente, sc) > 0) {
							System.out.println("Se ha añadido la orden del cliente");
						} else {
							System.out.println("No se ha añadido la orden del cliente");
						}
					} catch (IllegalArgumentException ex) {
						System.out.println(ex.getMessage());
					}
					break;
				case 8:
					codigoCliente = devolverEntero(sc, "Introduzca el codigo del cliente: ");
					double importe = devolverDouble(sc, "Introduzca el importe: ");
					try {
						if (cerrarIncidencia(url, user, pswd, codigoCliente, importe) > 0) {
							System.out.println("Se ha cerrado la incidencia");
						} else {
							System.out.println("No se ha podido cerrar la incidencia");
						}
					} catch (IllegalArgumentException ex) {
						System.out.println(ex.getMessage());
					}
					break;
				case 9:
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
				if (accion < 1 || accion > accion_max) {
					System.out.println(String.format("Se debe introducir una accion valida (1-%d)", accion_max));
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

	public static int addCliente(String url, String user, String pswd, String nombre, String email, int saldo,
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

	public static int removeCliente(String url, String user, String pswd, String nombre, String email) {
		int rs = 0;
		try (Connection con = DriverManager.getConnection(url, user, pswd); Statement stmt = con.createStatement()) {
			String sql = String.format("DELETE FROM cliente where nombre like '%s' and email like '%s'", nombre, email);
			rs = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}

	public static void selectAllOrdens(String url, String user, String pswd) {
		try (ResultSet rs2 = DriverManager.getConnection(url, user, pswd).createStatement()
				.executeQuery("SELECT * FROM ordenes")) {
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

	public static void selectAllClientes(String url, String user, String pswd) {
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

	public static String selectCliente(String url, String user, String pswd, String nombre, String email) {
		String resultado = "";
		try (Connection conn = DriverManager.getConnection(url, user, pswd);
				PreparedStatement ps = conn
						.prepareStatement("SELECT * FROM cliente where nombre like ? and email like ?");) {
			ps.setString(1, nombre);
			ps.setString(2, email);
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					resultado = String.format(
							"codigo:%s\tnombre:%s\temail:%s\tsaldo:%s\tfecha_alta:%s\tfecha_creacion:%s\tfecha_actualizacion:%s\n",
							rs.getString("codigo"), rs.getString("nombre"), rs.getString("email"),
							rs.getString("saldo"), rs.getString("fecha_alta"), rs.getString("fecha_creacion"),
							rs.getString("fecha_actualizacion"));
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return resultado;

	}

	public static int updateCliente(String url, String user, String pswd, String nombre, String email, String campo,
			String campoMod) {
		int filasModificadas = 0;
		try (Connection con = DriverManager.getConnection(url, user, pswd);
				PreparedStatement ps = con.prepareStatement(
						String.format("UPDATE cliente SET %s=? WHERE nombre like ? and email like ? ", campo))) {
			try {
				ps.setString(1, campoMod);
				ps.setString(2, nombre);
				ps.setString(3, email);
				filasModificadas = ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return filasModificadas;
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

	public static int añadirOrdenCliente(String url, String user, String pswd, int codigoCliente, Scanner sc)
			throws IllegalArgumentException {
		boolean existe = false;
		int resultado = 0;
		try (Connection conn = DriverManager.getConnection(url, user, pswd);
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM cliente where codigo = ?");) {
			ps.setInt(1, codigoCliente);

			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					existe = true;
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
		if (!existe) {
			throw new IllegalArgumentException("Se ha introducido un codigo de cliente no valido");
		} else {
			String asunto = devolverString(sc, "Introduzca el asunto: ");
			String descripcion = devolverString(sc, "Introduzca la descripcion: ");
			LocalDate fechaApertura = LocalDate.now();

			try (Connection conn = DriverManager.getConnection(url, user, pswd);
					PreparedStatement ps = conn
							.prepareStatement("INSERT into ordenes values (NULL,?,?,?,?,NULL,NULL)");) {

				ps.setInt(1, codigoCliente);
				ps.setString(2, asunto);
				ps.setString(3, descripcion);
				ps.setString(4, fechaApertura.toString());

				try {
					resultado = ps.executeUpdate();
				} catch (SQLTimeoutException ex) {
					System.out.println(ex.getMessage());
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
				}

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		return resultado;
	}

	public static void selectAllOrdenes(String url, String user, String pswd) {
		try (ResultSet rs2 = DriverManager.getConnection(url, user, pswd).createStatement()
				.executeQuery("SELECT * FROM ordenes")) {
			try {
				while (rs2.next()) {
					System.out.printf("codigo_incidencia:%s\t", rs2.getString("codigo_incidencia"));
					System.out.printf("codigo_cliente:%s\t", rs2.getString("codigo_cliente"));
					System.out.printf("asunto:%s\t", rs2.getString("asunto"));
					System.out.printf("descripcion:%s\t", rs2.getString("descripcion"));
					System.out.printf("fecha_apertura:%s\t", rs2.getString("fecha_apertura"));
					System.out.printf("fecha_cierre:%s\t", rs2.getString("fecha_cierre"));
					System.out.printf("importe:%s\n", rs2.getString("importe"));
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public static int cerrarIncidencia(String url, String user, String pswd, int codigoCliente, double importe)
			throws IllegalArgumentException {
		boolean existe = false;
		try (Connection conn = DriverManager.getConnection(url, user, pswd);
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM cliente where codigo = ?");) {
			ps.setInt(1, codigoCliente);

			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					existe = true;
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
		if (!existe) {
			throw new IllegalArgumentException("Se ha introducido un codigo de cliente no valido");
		} else {
			int filasModificadas = 0;
			try (Connection con = DriverManager.getConnection(url, user, pswd);
					PreparedStatement ps = con.prepareStatement(
							String.format("UPDATE ordenes SET fecha_cierre=?, importe=? WHERE codigo_cliente = ?"))) {
				try {
					ps.setString(1, LocalDate.now().toString());
					ps.setDouble(2, importe);
					ps.setInt(3, codigoCliente);
					filasModificadas = ps.executeUpdate();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return filasModificadas;
		}
	}
}
