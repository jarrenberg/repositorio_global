package hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.entity.Persona;
import hibernate.util.HibernateUtil;
import hibernate.util.UtilidadesSQL;

public class App {
	public static void main(String[] args) {
		menu();

	}/*
		 * Persona p1 = new Persona("Jorge", "C/Molona",
		 * "jorge@email.com","123456789",187); Persona p2 = new Persona("Juan",
		 * "C/Augusto", "juan@email.com","987654321",178); Transaction transaction =
		 * null; try (Session session = HibernateUtil.getSessionFactory().openSession())
		 * { // start a transaction transaction = session.beginTransaction(); // save
		 * the student objects session.persist(p1); session.persist(p2); // commit
		 * transaction transaction.commit(); } catch (Exception e) { if (transaction !=
		 * null) { transaction.rollback(); } e.printStackTrace(); }
		 * 
		 * try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		 * List<Persona> agenda = session.createQuery("from Persona",
		 * Persona.class).list(); agenda.forEach(s -> System.out.println(s.toString()));
		 * } catch (Exception e) { e.printStackTrace(); }
		 * 
		 * HibernateUtil.shutdown();
		 */

	public static void menu() {
		try (Scanner sc = new Scanner(System.in)) {
			boolean salir = false;
			do {
				List<Persona> agenda = getAgenda();
				System.out.println("\n****** MENU AGENDA: ******\n");
				System.out.println("a) INSERTAR CONTACTO");
				System.out.println("b) MOSTRAR CONTACTOS");
				System.out.println("c) ACTUALIZAR CONTACTO");
				System.out.println("d) BORRAR CONTACTO");
				System.out.println("e) SALIR");

				String accion = UtilidadesSQL.devolverString(sc, "");

				switch (accion) {
				case "a":
					String nombre = UtilidadesSQL.devolverString(sc, "Introduzca el nombre del contacto:");
					String direccion = UtilidadesSQL.devolverString(sc, "Introduzca la direccion del contacto:");
					String email = UtilidadesSQL.devolverString(sc, "Introduzca el email del contacto:");
					String telefono = UtilidadesSQL.devolverString(sc, "Introduzca el telefono del contacto:");
					int altura = UtilidadesSQL.devolverEntero(sc, "Introduzca la altura del contacto:");

					try {
						Persona p = new Persona(nombre, direccion, email, telefono, altura);
						boolean insertado = insertarPersona(p);
						if (insertado) {
							System.out.println("Se ha insertado a la persona en la agenda con exito!");
						} else {
							System.out.println("No se ha podido insertar a la persona en la agenda");
						}
					} catch (Exception ex) {
						System.out.printf("Error: %s\n", ex.getMessage());
					}

					break;
				case "b":
					agenda.forEach(s -> System.out.println(s.toString()));
					break;
				case "c":

					break;
				case "d":
					nombre = UtilidadesSQL.devolverString(sc, "Introduzca el nombre del contacto a eliminar:");
					direccion = UtilidadesSQL.devolverString(sc, "Introduzca la direccion del contacto a eliminar:");
					email = UtilidadesSQL.devolverString(sc, "Introduzca el email del contacto a eliminar:");
					telefono = UtilidadesSQL.devolverString(sc, "Introduzca el telefono del contacto a eliminar:");
					altura = UtilidadesSQL.devolverEntero(sc, "Introduzca la altura del contacto a eliminar:");
					try {
						Persona p = new Persona(nombre, direccion, email, telefono, altura);
						boolean elimminado = eliminarPersona(p);
						if (elimminado) {
							System.out.println("Se ha eliminado a la persona en la agenda con exito!");
						} else {
							System.out.println("No se ha podido eliminado a la persona en la agenda");
						}
					} catch (Exception ex) {
						System.out.printf("Error: %s\n", ex.getMessage());
					}
					break;

				case "e":
					System.out.println("SALIENDO ...");
					salir = true;
					break;

				}
			} while (!salir);
		}
	}

	public static boolean insertarPersona(Persona p) {
		boolean insertado = false;
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student objects
			session.persist(p);
			// commit transaction
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

	public static List<Persona> getAgenda() {
		List<Persona> agenda = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			agenda = session.createQuery("from Persona", Persona.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agenda;
	}

	public static boolean eliminarPersona(Persona p) {
	    boolean eliminado = false;
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        
	        // This handles both managed and detached entities
	        session.remove(session.merge(p));
	        
	        transaction.commit();
	        eliminado = true;
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return eliminado;
	}
}
