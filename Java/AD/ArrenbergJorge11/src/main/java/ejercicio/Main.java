package ejercicio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws SQLException {
		menu();
	}

	public static void menu() throws SQLException {
		String conexion = "jdbc:sqlite:C:\\DAM\\2º DAM\\Java\\AD\\ArrenbergJorge11\\mantenimiento.db";
		boolean salir = false;
		try (Scanner sc = new Scanner(System.in)) {
			do {
				ArrayList<Object> parametros = new ArrayList<>();
				parametros.clear();
				System.out.println("\n\n*** MENU ***");
				System.out.println("1) Mostrar tabla");
				System.out.println("2) Añadir elemento a equipos");
				System.out.println("3) Añadir elemento a piezas");
				System.out.println("4) Actualizar elemento de equipos");
				System.out.println("5) Actualizar elemento de piezas");
				System.out.println("6) Eliminar elemento de equipos");
				System.out.println("7) Eliminar elemento de piezas");
				System.out.println("8) Salir");

				int accion = UtilidadesSQLite.devolverEntero(sc, "Introduzca la accion:");
				switch (accion) {
				case 1:
					String tabla = UtilidadesSQLite.devolverString(sc,
							"Introduzca el nombre de la tabla que le gustaria mostrar: ");
					UtilidadesSQLite.mostrarTodosRegistrosTabla(tabla, conexion);
					break;
				case 2:
					String codigoEquipo = UtilidadesSQLite.devolverString(sc,
							"Introduzca el codigo del equipo a añadir:  ");
					String modelo = UtilidadesSQLite.devolverString(sc, "Introduzca el modelo del equipo a añadir:  ");
					String descripcion = UtilidadesSQLite.devolverString(sc,
							"Introduzca la descripcion del equipo a añadir:  ");
					parametros.add(codigoEquipo);
					parametros.add(modelo);
					parametros.add(descripcion);
					if (UtilidadesSQLite.anyadirABaseDeDatos("equipos", conexion, parametros)) {
						System.out.println("Se ha añadido el equipo a la base de datos");
					} else {
						System.out.println("No se ha añadido el equipo a la base de datos");
					}
					break;
				case 3:
					String codigoPieza = UtilidadesSQLite.devolverString(sc,
							"Introduzca el codigo de la pieza a añadir:  ");
					String tipo = UtilidadesSQLite.devolverString(sc, "Introduzca el tipo de la pieza a añadir:  ");
					descripcion = UtilidadesSQLite.devolverString(sc,
							"Introduzca la descripcion de la pieza a añadir:  ");
					int unidades = UtilidadesSQLite.devolverEntero(sc,
							"Introduzca las unidades de la pieza a añadir:  ");
					codigoEquipo = UtilidadesSQLite.devolverString(sc,
							"Introduzca el codigo de equipo de la pieza a añadir:  ");

					parametros.add(codigoPieza);
					parametros.add(tipo);
					parametros.add(descripcion);
					parametros.add(unidades);
					parametros.add(codigoEquipo);
					if (UtilidadesSQLite.anyadirABaseDeDatos("piezas", conexion, parametros)) {
						System.out.println("Se ha añadido la pieza a la base de datos");
					} else {
						System.out.println("No se ha añadido la pieza a la base de datos");
					}

					break;
				case 4:
					codigoEquipo = UtilidadesSQLite.devolverString(sc,
							"Introduzca el codigo del equipo que le gustaria modificar: ");
					String campo = UtilidadesSQLite.devolverString(sc,
							"Introduzca el campo que le gustaria modificar: ");
					String valor = UtilidadesSQLite.devolverString(sc, "Introduzca el nuevo valor: ");
					if (UtilidadesSQLite.modificarRegistroBaseDeDatos("equipos", conexion, "codigo", codigoEquipo,
							campo, valor)) {
						System.out.println("El equipo ha sido modificado");
					} else {
						System.out.println("El equipo no ha sido modificado");
					}

					break;
				case 5:
					codigoPieza = UtilidadesSQLite.devolverString(sc,
							"Introduzca el codigo de la pieza que le gustaria modificar: ");
					campo = UtilidadesSQLite.devolverString(sc, "Introduzca el campo que le gustaria modificar: ");
					valor = UtilidadesSQLite.devolverString(sc, "Introduzca el nuevo valor: ");
					if (UtilidadesSQLite.modificarRegistroBaseDeDatos("piezas", conexion, "codigo", codigoPieza, campo,
							valor)) {
						System.out.println("El equipo ha sido modificado");
					} else {
						System.out.println("El equipo no ha sido modificado");
					}
					break;
				case 6:
					campo = UtilidadesSQLite.devolverString(sc,
							"Introduzca el campo por el cual filtraremos para eliminar el equipo: ");
					valor = UtilidadesSQLite.devolverString(sc, "Introduzca el valor que tiene dicho campo: ");
					if (UtilidadesSQLite.eliminarRegistroBaseDeDatos("equipos", "piezas", conexion, campo, valor,
							"codigo", "equipo_codigo")) {
						System.out.println("Se ha eliminado el equipo de la base de datos");
					} else {
						System.out.println("No se ha podido eliminar el equipo de la base de datos");
					}
					break;
				case 7:
					campo = UtilidadesSQLite.devolverString(sc,
							"Introduzca el campo por el cual filtraremos para eliminar la pieza: ");
					valor = UtilidadesSQLite.devolverString(sc, "Introduzca el valor que tiene dicho campo: ");
					if (UtilidadesSQLite.eliminarRegistroBaseDeDatos("piezas", conexion, campo, valor, "codigo")) {
						System.out.println("Se ha eliminado la pieza de la base de datos");
					} else {
						System.out.println("No se ha podido eliminar la pieza de la base de datos");
					}
					break;
				case 8:
					System.out.println("Saliendo ...");
					salir = true;
					break;
				default:
					System.out.println("Se ha introducido una accion invalida");
				}
			} while (!salir);
		}
	}
}
// INSERT INTO "main"."equipos" VALUES ('EQ-05','test','modelotest');
// INSERT INTO "main"."piezas" VALUES ('PI01','locotron','terano','12','EQ-05');
// INSERT INTO "main"."piezas" VALUES ('PI02','locotron2','terano3','1','EQ-05');
// INSERT INTO "main"."equipos" VALUES ('EQ-06','parole','ventilador');
// INSERT INTO "main"."piezas" VALUES ('PI05','cartera','fundicion','6','EQ-06');