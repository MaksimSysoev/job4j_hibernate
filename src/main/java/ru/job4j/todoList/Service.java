package ru.job4j.todoList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.Function;

public class Service {

    private SessionFactory factory = new Configuration()
            .configure()
            .buildSessionFactory();

    public Service() {

    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }


    public void add(Item item) {
        this.tx(session -> session.save(item));
    }

    public List<Item> select() {
      return  this.tx(
                session->session.createQuery("from Item").list()
        );
    }

}
