package ru.job4j.carStorage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class Service {

    private static Service instance = new Service();
    public static Service getInstance() {
        return instance;
    }

    private SessionFactory factory = new Configuration()
            .configure()
            .buildSessionFactory();

    public Service() {
        createTables();

        if (select("User").isEmpty()) {
            User user = new User();
            user.setLogin("admin");
            user.setPassword("admin");
            add(user);
        }

        if (select("Brand").isEmpty()) {
            Brand brand = new Brand();
            brand.setName("Audi");
            add(brand);
            brand.setName("Nissan");
            add(brand);
            brand.setName("Renault");
            add(brand);
        }

        if (select("Body").isEmpty()) {
            Body body = new Body();
            body.setName("Седан");
            add(body);
            body.setName("Хэтчбек");
            add(body);
        }

        if (select("Model").isEmpty()) {
            Model model = new Model();
            model.setName("A3");
            model.setBrand(new Brand(1));
            model.setBody(new Body(2));
            add(model);
            model.setName("A4");
            model.setBrand(new Brand(1));
            model.setBody(new Body(1));
            add(model);
            model.setName("A6");
            model.setBrand(new Brand(2));
            model.setBody(new Body(1));
            add(model);
            model.setName("GTR");
            model.setBrand(new Brand(2));
            model.setBody(new Body(1));
            add(model);
            model.setName("Logan");
            model.setBrand(new Brand(3));
            model.setBody(new Body(1));
            add(model);
            model.setName("Fluence");
            model.setBrand(new Brand(3));
            model.setBody(new Body(1));
            add(model);
            model.setName("Megane");
            model.setBrand(new Brand(3));
            model.setBody(new Body(1));
            add(model);
            model.setName("Megane");
            model.setBrand(new Brand(3));
            model.setBody(new Body(2));
            add(model);
            model.setName("Clio");
            model.setBrand(new Brand(3));
            model.setBody(new Body(2));
            add(model);
        }

        if (select("Ads").isEmpty()) {
            Ads ads = new Ads();
            ads.setName("Объявление 1");
            ads.setCreated(new Date());
            ads.setActive(true);
            ads.setBrand(new Brand(1));
            ads.setModel(new Model(1));
            ads.setBody(new Body(1));
            add(ads);
            ads.setName("Объявление 2");
            ads.setCreated(new Date());
            ads.setActive(true);
            ads.setBrand(new Brand(2));
            ads.setModel(new Model(4));
            ads.setBody(new Body(1));
            add(ads);
            ads.setName("Объявление 3");
            ads.setCreated(new Date());
            ads.setActive(true);
            ads.setBrand(new Brand(3));
            ads.setModel(new Model(5));
            ads.setBody(new Body(1));
            add(ads);
            ads.setName("Объявление 4");
            ads.setCreated(new Date());
            ads.setActive(true);
            ads.setBrand(new Brand(3));
            ads.setModel(new Model(9));
            ads.setBody(new Body(2));
            add(ads);

        }

        if (select("Photo").isEmpty()) {
            Photo photo = new Photo();
            photo.setName("photo1");
            photo.setAds(new Ads(1));
            add(photo);
            photo.setName("photo2");
            photo.setAds(new Ads(1));
            add(photo);
            photo.setName("photo3");
            photo.setAds(new Ads(2));
            add(photo);
            photo.setName("photo4");
            photo.setAds(new Ads(3));
            add(photo);
            photo.setName("photo5");
            photo.setAds(new Ads(4));
            add(photo);
            photo.setName("photo6");
            photo.setAds(new Ads(4));
            add(photo);
            photo.setName("photo7");
            photo.setAds(new Ads(4));
            add(photo);
        }

    }

    public <T> T tx(final Function<Session, T> command) {
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

    public void createTables() {
        // users
        this.tx(
                session -> session.createSQLQuery("create table if not exists " +
                        "public.users(id serial primary key, " +
                        "login varchar(100), " +
                        "password varchar(100))")
                        .executeUpdate()
        );

        // brand (id, name)
        this.tx(
                session -> session.createSQLQuery("create table if not exists " +
                        "public.brand(id serial primary key, " +
                        "name varchar(100))")
                        .executeUpdate()
        );

        // body(id, name)
        this.tx (
                session -> session.createSQLQuery("create table if not exists " +
                        "public.body(id serial primary key, " +
                        "name varchar(100))")
                        .executeUpdate()
        );



        // model  (id, name, brand_id, body_id)
        this.tx(
                session -> session.createSQLQuery("create table if not exists " +
                        "public.model(id serial primary key," +
                        "name varchar (100)," +
                        "brand_id int references brand(id)," +
                        "body_id int references body(id))")
                        .executeUpdate()
        );


        // ads (id, name, brand, model, created, active)
        this.tx (
                session -> session.createSQLQuery("create table if not exists " +
                        "public.ads(id serial primary key, " +
                        "name varchar(100), " +
                        "created timestamp," +
                        "active bool," +
                        "brand_id int references brand(id), " +
                        "model_id int references model(id)," +
                        "body_id int references body(id))")
                        .executeUpdate()
        );

        // photo(id, name, ads_id)
        this.tx(
                session -> session.createSQLQuery("create table if not exists " +
                        "public.photo(id serial primary key," +
                        "name varchar (200)," +
                        "ads_id int references ads(id))")
                        .executeUpdate()
        );


    }



    public void add(Pts pts) {
        this.tx(session -> session.save(pts));
    }


    public void update(Pts pts) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            session.update(pts);
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }



    public List<Pts> select(String table) {
        return  this.tx(
                session->session.createQuery("from "+table).list()
        );
    }

    public boolean isCredentional(String login, String password) {
        return this.tx(
                session -> session.createQuery("from User where login='"+login+"' and password='"+password+"'").list().isEmpty()
        );
    }

}