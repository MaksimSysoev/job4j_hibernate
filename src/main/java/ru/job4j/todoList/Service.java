package ru.job4j.todoList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class Service {

    private SessionFactory factory = new Configuration()
            .configure()
            .buildSessionFactory();
    private Session session;

    public Service() {
        this.session = factory.openSession();
    }

    public void add(Item item) {
        this.session.save(item);
        close();
    }

    public List<Item> select() {
        return this.session.createQuery("from Item").list();
    }

    public void close() {
        this.session.getTransaction().commit();
        this.session.close();
        this.factory.close();
    }

}
