package ru.job4j.hibernateRun;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

public class HibernateRun {

    public void insert(Session session, User user) {
        session.save(user);
    }

    public void update(Session session, User user) {
        session.update(user);
    }

    public void delete(Session session, User user) {
        session.delete(user);
    }

    public void select (Session session) {
        System.out.println(session.createQuery("from User").list());
    }

    public static void main(String[] args) {
        HibernateRun hr = new HibernateRun();
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        User user = new User();
       // user.setLogin("User1");
       // hr.insert(session, user);

       // user.setId(1);
       // user.setLogin("User2");
       // hr.update(session, user);

       //   user.setId(3);
       //   hr.delete(session, user);

        hr.select(session);

        session.getTransaction().commit();
        session.close();
        factory.close();
    }

}
