package hibernate;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import hibernate.entity.Cancion;
import hibernate.entity.Cantante;
import hibernate.util.UtilidadesSQL;
import hibernate.util.DAO.CancionDAO;
import hibernate.util.DAO.CantanteDAO;

public class App {
	public static void main(String[] args) {
		menu();
	}

	public static void menu() {

		try (Scanner sc = new Scanner(System.in)) {
			boolean salir = false;
			do {
				System.out.println("\n*** MENU ****");
				System.out.println("a) INSERTAR CANTANTE");
				System.out.println("b) INSERTAR CANCION");
				System.out.println("c) CONSULTAR CANTANTE");
				System.out.println("d) LISTADO DE CANTANTES");
				System.out.println("e) TODAS LAS CANCIONES DE UN CANTANTE");
				System.out.println("f) BUSCAR CANCION POR TITULO");
				System.out.println("g) NUMERO DE CANCIONES CON EL TITULO");
				System.out.println("h) TODOS LOS CANTANTES Y SUS CANCIONES");
				System.out.println("i) CANCIONES ENTRE DOS FECHAS");
				System.out.println("j) SALIR\n");

				String accion = UtilidadesSQL.devolverString(sc, "");
				accion = accion.toLowerCase();
				switch (accion) {
				case "a":
					String nombre = UtilidadesSQL.devolverString(sc, "Introduzca el nombre del cantante: ");
					String pais = UtilidadesSQL.devolverString(sc, "Introduzca el pais del cantante: ");
					Cantante ca = new Cantante(nombre, pais);
					if (CantanteDAO.insertarCantante(ca)) {
						System.out.println("Cantante insertado!");
					} else {
						System.out.println("No se ha podido insertar al cantante");
					}
					break;
				case "b":
					String titulo = UtilidadesSQL.devolverString(sc, "Introduzca el titulo de la cancion: ");
					int anyo = UtilidadesSQL.devolverEntero(sc, "Introduzca el año en el que se lanzó la cancion: ");
					nombre = UtilidadesSQL.devolverString(sc, "Introduzca el nombre del cantante: ");
					pais = UtilidadesSQL.devolverString(sc, "Introduzca el pais del cantante: ");
					ca = new Cantante(nombre, pais);
					Cancion c = new Cancion(titulo, anyo, ca);
					if (CancionDAO.insertarCancion(c, ca)) {
						System.out.println("Cancion insertada!");
					} else {
						System.out.println("No se ha podido insertar la cancion");
					}
					break;
				case "c":
					nombre = UtilidadesSQL.devolverString(sc, "Introduzca el nombre del cantante: ");
					pais = UtilidadesSQL.devolverString(sc, "Introduzca el pais del cantante: ");
					ca = new Cantante(nombre, pais);
					try {
						System.out.println(CantanteDAO.getInfoCantante(ca));
					} catch (NoSuchElementException ex) {
						System.out.println(ex.getMessage());
					}
					break;
				case "d":
					System.out.println(CantanteDAO.getListadoCantantes());
					break;
				case "e":

					nombre = UtilidadesSQL.devolverString(sc, "Introduzca el nombre del cantante: ");
					pais = UtilidadesSQL.devolverString(sc, "Introduzca el pais del cantante: ");
					Cantante c_e = CantanteDAO.getCantante(nombre, pais);
					if (c_e != null) {
						System.out.println(CantanteDAO.getAllCancionesDeCantante(c_e));
					} else {
						System.out.println("No existe dicho cantante");
					}
					break;
				case "f":
					titulo = UtilidadesSQL.devolverString(sc, "Introduzca el titulo de la cancion: ");
					System.out.println(CancionDAO.getCancionesPorTitulo(titulo));
					break;
				case "g":
					titulo = UtilidadesSQL.devolverString(sc, "Introduzca el titulo de la cancion: ");
					System.out.printf("El numero de canciones con '%s' en el titulo es %d\n", titulo,
							CancionDAO.getNumeroCancionesPorTitulo(titulo));
					break;
				case "h":
					System.out.println(CantanteDAO.getAllCantantesYNumeroDeCanciones());
					break;
				case "i":
					int fecha1 = UtilidadesSQL.devolverEntero(sc, "Introduzca un año: ");
					int fecha2 = UtilidadesSQL.devolverEntero(sc, "Introduzca otro año: ");
					List<Cancion> canciones = CancionDAO.getListaCancionesEntreDosFechas(fecha1, fecha2);
					for (Cancion c_i : canciones) {
						System.out.println(c_i.toString());
					}
					break;
				case "j":
					salir = true;
					System.out.println("SALIENDO...");
					break;

				default:
					System.out.println("HAS INTROUDCIDO UNA OPCION NO VALIDA");
				}
			} while (!salir);
		}
	}

}
