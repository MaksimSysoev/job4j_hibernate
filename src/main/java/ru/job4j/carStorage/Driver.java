package ru.job4j.carStorage;

import javax.persistence.*;
import java.util.HashSet;
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
}
