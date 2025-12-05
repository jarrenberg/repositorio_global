package hibernate.util.DAO;

import java.util.List;
import java.util.NoSuchElementException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.entity.Cancion;
import hibernate.entity.Cantante;
import hibernate.util.HibernateUtil;

public class CantanteDAO {

	public static Cantante getCantante(String nombre, String pais) {
		Cantante c = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			List<Cantante> cantantes = session.createQuery("from Cantante", Cantante.class).list();
			for (Cantante cantante : cantantes) {
				if (cantante.getNombre().equalsIgnoreCase(nombre) && cantante.getPais().equalsIgnoreCase(pais)) {
					c = cantante;
				}
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

		return c;
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

	public static String getInfoCantante(Cantante cantante) {
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

	public static String getListadoCantantes() {
		String resultado = "";
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			List<Cantante> cantantes = session.createQuery("from Cantante", Cantante.class).list();
			for (Cantante c : cantantes) {
				resultado = String.format("%s\n%s\n", resultado, c.toString());
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

	public static String getAllCancionesDeCantante(Cantante c) {
		String resultado = String.format("Canciones de %s:\n", c.getNombre());
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			String sentenciaHQL = String.format("from Cancion as ca  where ca.cantante.id=%d", c.getId());
			List<Cancion> canciones = session.createQuery(sentenciaHQL, Cancion.class).list();
			if (canciones.size() == 0) {
				resultado = String.format("El cantante %s no tiene canciones", c.getNombre());
			} else {
				for (Cancion cc : canciones) {
					resultado = String.format("%s%s\n", resultado, cc.toString());
				}
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return resultado;
	}

	public static String getAllCantantesYNumeroDeCanciones() {
		String resultado = "";
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String sentenciaHQL = String.format("from Cantante");
			List<Cantante> cantantes = session.createQuery(sentenciaHQL, Cantante.class).list();
			for (Cantante c : cantantes) {
				resultado = String.format("%s\nNombre: %s, Pais, %s, Numero de canciones: %d \n", resultado,
						c.getNombre(), c.getPais(), c.getRepertorio().size());
			}
			if (cantantes.size() == 0) {
				resultado = "No existen cantantes";
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
