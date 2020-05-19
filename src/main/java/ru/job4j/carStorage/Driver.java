package ru.job4j.carStorage;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="driver")
public class Driver implements Pts{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinTable(name="history_owner",
            joinColumns = @JoinColumn(name="driver_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="car_id", referencedColumnName="id")
    )
    private Set<Car> cars;

    public Driver() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Brand getBrand() {
        return null;
    }

    @Override
    public Model getModel() {
        return null;
    }

    @Override
    public Body getBody() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return id == driver.id &&
                Objects.equals(cars, driver.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cars);
    }
}
