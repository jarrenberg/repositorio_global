package hibernate;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.entity.Cancion;
import hibernate.entity.Cantante;
import hibernate.util.HibernateUtil;
import hibernate.util.UtilidadesSQL;

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
				System.out.println("e) SALIR\n");

				String accion = UtilidadesSQL.devolverString(sc, "");
				accion = accion.toLowerCase();
				switch (accion) {
				case "a":
					String nombre = UtilidadesSQL.devolverString(sc, "Introduzca el nombre del cantante: ");
					String pais = UtilidadesSQL.devolverString(sc, "Introduzca el pais del cantante: ");
					Cantante ca = new Cantante(nombre, pais);
					if (insertarCantante(ca)) {
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
					if (insertarCancion(c, ca)) {
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
						System.out.println(infoCantante(ca));
					} catch (NoSuchElementException ex) {
						System.out.println(ex.getMessage());
					}
					break;
				case "d":
					System.out.println(listadoCantantes());
					break;
				case "e":
					salir = true;
					System.out.println("SALIENDO...");
					break;

				default:
					System.out.println("HAS INTROUDCIDO UNA OPCION NO VALIDA");
				}
			} while (!salir);
		}
	}

	public static boolean insertarCancion(Cancion cancion, Cantante cantante) {
		boolean insertado = false;
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			List<Cantante> cantantes = session.createQuery("from Cantante", Cantante.class).list();
			boolean existe = false;
			for (Cantante c : cantantes) {
				if (cantante.equals(c)) {
					System.out.println("Igual");
					existe = true;
					c.agregarCancion(cancion);
					session.merge(c);
				}
			}
			if (!existe) {
				session.persist(cantante);
				cantante.agregarCancion(cancion);
			}

			transaction.commit();
			insertado = true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return insertado;
	}

	public static boolean insertarCantante(Cantante c) {
		boolean insertado = false;
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			List<Cantante> cantantes = session.createQuery("from Cantante", Cantante.class).list();
			boolean existe = false;
			for (Cantante cantante : cantantes) {
				if (c.equals(cantante)) {
					existe = true;
				}
			}
			if (!existe) {
				session.persist(c);
				transaction.commit();
				insertado = true;
			}

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return insertado;
	}

	public static String infoCantante(Cantante cantante) {
		String resultado = "";
		Transaction transaction = null;
		boolean existe = false;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			List<Cantante> cantantes = session.createQuery("from Cantante", Cantante.class).list();
			for (Cantante c : cantantes) {
				if (c.equals(cantante)) {
					existe = true;
					resultado = String.format("%s\n", c.toString());
				}
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		if (!existe) {
			throw new NoSuchElementException("No existe dicho cantante");
		}
		return resultado;
	}

	public static String listadoCantantes() {
		String resultado = "";
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			List<Cantante> cantantes = session.createQuery("from Cantante", Cantante.class).list();
			for (Cantante c : cantantes) {
				resultado=String.format("%s\n%s\n", resultado,c.toString());
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return resultado;
	}
}
