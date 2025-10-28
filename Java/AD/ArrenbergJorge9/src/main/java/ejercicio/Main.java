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
		UtilidadesSQL.mostrarTodosRegistrosTabla("CUENTAS");
		Scanner sc = new Scanner(System.in);
		String[] info = informacionTransaccion(sc);
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
		try (Connection con = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps1 = con.prepareStatement("UPDATE CUENTAS SET saldo=? where id_cuenta=?");
				PreparedStatement ps2 = con.prepareStatement("UPDATE CUENTAS SET saldo=? where id_cuenta=?")) {
			try {
				con.setAutoCommit(false);
				if ((UtilidadesSQL.existeEntryParamString(info[0], "id_cuenta", "CUENTAS"))
						&& (UtilidadesSQL.existeEntryParamString(info[1], "id_cuenta", "CUENTAS"))
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
						con.setAutoCommit(true);
						System.out.println("Transacción revertida debido a un error.");
					}
				} catch (SQLException rollbackEx) {
					rollbackEx.printStackTrace();
				}
				ex.printStackTrace();
			}

		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}

	public static String[] informacionTransaccion(Scanner sc) {
		String[] resultado = new String[3];
		resultado[0] = UtilidadesSQL.devolverString(sc, "Introduzca la cuenta de origen: ");
		resultado[1] = UtilidadesSQL.devolverString(sc, "Introduzca la cuenta de destino: ");
		resultado[2] = String.format("%d", UtilidadesSQL.devolverEntero(sc, "Introduzca la cantidad a traspasar: "));
		return resultado;
	}

	public static int getSaldoCuenta(String idCuenta) {
		int saldo = 0;
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();
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