package ru.job4j.carStorage;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="car")
public class Car implements Pts{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private Engine engine;

    @ManyToMany(mappedBy = "cars")
    private Set<Driver> drivers;

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Engine getEngine_id() {
        return engine;
    }

    public void setEngine_id(Engine engine_id) {
        this.engine = engine_id;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }
}
