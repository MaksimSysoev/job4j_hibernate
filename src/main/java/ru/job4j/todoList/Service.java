package ru.job4j.todoList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Service {

    private SessionFactory factory = new Configuration()
            .configure()
            .buildSessionFactory();

    public Service() {

    }

    public void add(Item item) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            session.save(item);
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public List<Item> select() {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            final Query query = session.createQuery("from Item");
            tx.commit();
            return query.list();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

}
