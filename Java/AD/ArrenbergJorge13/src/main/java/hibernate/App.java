package hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.entity.Persona;
import hibernate.util.HibernateUtil;

public class App {
    public static void main(String[] args) {

        Persona p1 = new Persona("Ramesh", "Fadatare", "rameshfadatare@javaguides.com");
        Persona p2 = new Persona("John", "Cena", "john@javaguides.com");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.persist(p1);
            session.persist(p2);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Persona> agenda = session.createQuery("from Persona", Persona.class).list();
            agenda.forEach(s -> System.out.println(s.getFirstName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

