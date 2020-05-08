package ru.job4j;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.carStorage.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CarStorageTest {

    private ru.job4j.carStorage.Service service = new Service();


    @Before
    public void setUp() {
        Engine engine = new Engine();
        Car car = new Car();
        Driver driver = new Driver();
        HistoryOwner ho = new HistoryOwner();
        service.add(engine);
        car.setEngine_id(new Engine(8));
        service.add(car);
        service.add(driver);
        ho.setCarId(37);
        ho.setDriverId(14);
        service.add(ho);
    }

    @Test
    public void whenGetIdEngine() {
        int id = service.select("Engine").get(0).getId();
        assertThat(id, is(8));
    }

    @Test
    public void whenGetIdCar() {
        int id = service.select("Car").get(0).getId();
        assertThat(id, is(33));
    }

    @Test
    public void whenGetIdDriver() {
        int id = service.select("Driver").get(0).getId();
        assertThat(id, is(10));
    }

    @Test
    public void whenGetHistoryOwnersForDriver() {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Driver driver = session.get(Driver.class, 14);

        assertThat(driver.getCars().size(), is(1));
        tx.commit();
        session.close();
        factory.close();
    }

    @Test
    public void whenGetHistoryOwnersForCar() {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Car car = session.get(Car.class, 37);

        assertThat(car.getDrivers().size(), is(1));
        tx.commit();
        session.close();
        factory.close();
    }
}
