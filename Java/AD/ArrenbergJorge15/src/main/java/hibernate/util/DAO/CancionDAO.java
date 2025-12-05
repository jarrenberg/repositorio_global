package hibernate.util.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.entity.Cancion;
import hibernate.entity.Cantante;
import hibernate.util.HibernateUtil;

public class CancionDAO {

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

	public static String getCancionesPorTitulo(String titulo) {
		String resultado = "";
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			String sentenciaHQL = String.format("from Cancion as ca where ca.titulo='%s'", titulo);
			List<Cancion> canciones = session.createQuery(sentenciaHQL, Cancion.class).list();
			if (canciones.size() == 0) {
				resultado = "No existe ninguna cancion con dicho titulo";
			} else {
				for (Cancion c : canciones) {
					resultado = String.format("%s%s\n", resultado, c.toString());
				}
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

	public static int getNumeroCancionesPorTitulo(String titulo) {
		List<Cancion> canciones = new ArrayList<Cancion>();
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			String sentenciaHQL = String.format("from Cancion as ca where ca.titulo='%s'", titulo);
			canciones = session.createQuery(sentenciaHQL, Cancion.class).list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return canciones.size();

	}

	public static List<Cancion> getListaCancionesEntreDosFechas(int fecha1, int fecha2) {
		List<Cancion> canciones = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			String sentenciaHQL = String.format("from Cancion as ca where ca.anyo between %s and %s", fecha1, fecha2);
			canciones = session.createQuery(sentenciaHQL, Cancion.class).list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return canciones;
	}
}
