package ejercicio;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] info = informacionTransaccion(sc);
		String[] credenciales = credencialesConexionSQL();
		try (Connection con = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = con.prepareStatement("UPDATE CUENTAS SET saldo=? where id_cuenta=?");
				PreparedStatement ps2 = con.prepareStatement("UPDATE CUENTAS SET saldo=? where id_cuenta=?")) {
			try {
				con.setAutoCommit(false);
				if ((existeCuenta(info[0])) && (existeCuenta(info[1]))
						&& (getSaldoCuenta(info[0]) - Integer.valueOf(info[2]) >= 0)) {
					ps1.setInt(1, getSaldoCuenta(info[0]) - Integer.valueOf(info[2]));
					ps1.setString(2, info[0]);
					ps2.setInt(1, getSaldoCuenta(info[1]) + Integer.valueOf(info[2]));
					ps2.setString(2, info[1]);

					ps1.executeUpdate();
					ps2.executeUpdate();
					con.commit();
					System.out.println("Se ha realizado la transaccion");
				} else {
					System.out.println("No se puede realizar la operacion");
				}
			} catch (SQLException ex) {
				// En caso de error, revertir la transacción
				try {
					if (con != null) {
						con.rollback();
						System.out.println("Transacción revertida debido a un error.");
					}
				} catch (SQLException rollbackEx) {
					rollbackEx.printStackTrace();
				}
				ex.printStackTrace();
			} finally {
				// Restaurar autocommit y cerrar conexión
				try {
					if (con != null) {
						con.setAutoCommit(true);
						con.close();
					}
				} catch (SQLException closeEx) {
					closeEx.printStackTrace();
				}
			}

		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
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

	public static String[] informacionTransaccion(Scanner sc) {
		String[] resultado = new String[3];
		resultado[0] = devolverString(sc, "Introduzca la cuenta de origen: ");
		resultado[1] = devolverString(sc, "Introduzca la cuenta de destino: ");
		resultado[2] = String.format("%d", devolverEntero(sc, "Introduzca la cantidad a traspasar: "));
		return resultado;
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

	public static boolean existeCuenta(String idCuenta) {
		boolean existe = false;
		String[] credenciales = credencialesConexionSQL();
		try (Connection con = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps = con.prepareStatement("SELECT * from CUENTAS where id_cuenta=?")) {
			ps.setString(1, idCuenta);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				existe = true;
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		return existe;
	}

	public static int getSaldoCuenta(String idCuenta) {
		int saldo = 0;
		String[] credenciales = credencialesConexionSQL();
		try (Connection con = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps = con.prepareStatement("SELECT saldo from CUENTAS where id_cuenta=?")) {
			ps.setString(1, idCuenta);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				saldo = rs.getInt(1);
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		return saldo;
	}
}