package ejercicio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Types;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		menu();
	}

	public static void menu() {
		try (Scanner sc = new Scanner(System.in)) {
			boolean salir = false;
			do {
				System.out.println("\n*** MENU ***\n");
				System.out.println("a) Insertar usuario");
				System.out.println("b) Numero de entradas del usuario");
				System.out.println("c) Inicio de sesion");
				System.out.println("d) Mostrar todos los usuarios");
				System.out.println("e) Salir\n");

				String accion = UtilidadesSQL.devolverString(sc, "Introduzca la accion a realizar: ").toLowerCase();

				switch (accion) {
				case ("a"):

					if (insertarUser(sc)) {
						System.out.println("Se ha insertado el usuario con exito!");
					} else {
						System.out.println("Ha ocurrido un error y no se ha podido insertar al usuario.");
					}
					break;
				case ("b"):
					String nombre_login = UtilidadesSQL.devolverString(sc, "Introduzca su nombre de login: ");
					int vecesLogin = getNumeroEntradasUser(nombre_login);
					if (vecesLogin < 0) {
						System.out.println("Ha ocurrido un error.");
					} else {
						System.out.printf("El numero de entradas del usuario %s son: %d\n", nombre_login, vecesLogin);
					}
					break;
				case ("c"):
					nombre_login = UtilidadesSQL.devolverString(sc, "Introduzca su nombre de login: ");
					String pswd = UtilidadesSQL.devolverString(sc, "Introduzca su contraseñan: ");
					if (loginUser(nombre_login, pswd)) {
						System.out.println("Se ha iniciado sesion con exito.");
					} else {
						System.out.println("No se ha podido iniciar sesion");
					}
					break;
				case ("d"):
					UtilidadesSQL.mostrarTodosRegistrosTabla("usuarios");
					break;

				case ("e"):
					System.out.println("Saliendo ...");
					salir = true;
					break;
				default:
					System.out.println("Se ha introducido una accion no valida.");
					break;
				}
			} while (!salir);
		}
	}

	public static boolean loginUser(String nombre_login, String pswd) {
		boolean iniciado = false;
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();

		try (Connection conn = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				CallableStatement cs = conn.prepareCall("CALL proc3(?,?)")) {

			cs.setString(1, nombre_login);
			cs.setString(2, pswd);

			try (ResultSet rs = cs.executeQuery()) {
				if (rs.next()) {
					iniciado = rs.getInt("success") == 1;
				}
			}

		} catch (SQLException ex) {
			System.out.println(ex.toString());
			iniciado = false;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			iniciado = false;
		}
		return iniciado;
	}

	public static int getNumeroEntradasUser(String nombre_login) {
		int resultado = -1;
		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();

		try (Connection conn = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				PreparedStatement ps = conn
						.prepareStatement("Select num_entradas from usuarios where nombre_login = ?")) {
			ps.setString(1, nombre_login);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultado = rs.getInt(1);
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return resultado;

	}

	public static boolean insertarUser(Scanner sc) {
		boolean insertado = false;
		String nombre_login = "";
		String contrasenya = "";
		String nombre_completo = "";
		do {
			nombre_login = UtilidadesSQL.devolverString(sc, "Introduzca el nombre de usuario: ");
		} while (nombre_login.length() > 12);
		do {
			contrasenya = UtilidadesSQL.devolverString(sc, "Introduzca la contraseña: ");
		} while (contrasenya.length() > 32);
		do {
			nombre_completo = UtilidadesSQL.devolverString(sc, "Introduzca el nombre completo del usuario: ");
		} while (nombre_completo.length() > 40);

		String[] credenciales = UtilidadesSQL.credencialesConexionSQL();

		try (Connection conn = DriverManager.getConnection(credenciales[0], credenciales[1], credenciales[2]);
				CallableStatement cs = conn.prepareCall("CALL proc1(?,?,?)")) {
			cs.setString(1, nombre_login);
			cs.setString(2, contrasenya);
			cs.setString(3, nombre_completo);
			cs.execute();
			insertado = true;
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return insertado;
	}
	
	// PROCEDIMIENTOS ALMACENADOS
	
	/*
	 * DROP PROCEDURE IF EXISTS proc3; DELIMITER // CREATE PROCEDURE proc3 ( IN
	 * p_nombre_login VARCHAR(12), IN p_contrasenya VARCHAR(32) ) BEGIN -- Primero
	 * verificar si existe IF EXISTS (SELECT 1 FROM usuarios WHERE nombre_login =
	 * p_nombre_login AND contrasenya = MD5(p_contrasenya)) THEN -- Si existe,
	 * actualizar e indicar éxito UPDATE usuarios SET num_entradas = num_entradas +
	 * 1 WHERE nombre_login = p_nombre_login AND contrasenya = MD5(p_contrasenya);
	 * SELECT 1 AS success; ELSE -- Si no existe, indicar fallo SELECT 0 AS success;
	 * END IF; END // DELIMITER ;
	 */
	/*
	 * DROP PROCEDURE IF EXISTS proc2; DELIMITER // CREATE PROCEDURE proc2 ( IN
	 * p_nombre_login VARCHAR(12) ) BEGIN select num_entradas from usuarios where
	 * nombre_login=p_nombre_login; END // DELIMITER ;
	 */
	/*
	 * DROP PROCEDURE IF EXISTS proc1; DELIMITER // CREATE PROCEDURE proc1 ( IN
	 * p_nombre_login VARCHAR(12), IN p_contrasenya VARCHAR(32), IN
	 * p_nombre_completo VARCHAR(40) ) BEGIN INSERT INTO usuarios VALUES (NULL,
	 * p_nombre_login, MD5(p_contrasenya), p_nombre_completo, 0); END // DELIMITER ;
	 */

	/*
	 * create table usuarios( cod_user int primary key auto_increment, nombre_login
	 * varchar(12) unique, contrasenya varchar(32), nombre_completo varchar(40),
	 * num_entradas int default 0 );
	 */
}
