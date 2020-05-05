package ru.job4j.carStorage;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="history_owner")
public class HistoryOwner implements Pts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="driver_id")
    private int driverId;

    @Column(name="car_id")
    private int carId;

    private Set<Driver> drivers = new HashSet<>();

    public HistoryOwner() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner", joinColumns = {
            @JoinColumn(name = "driver_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "car_id",
                    nullable = false, updatable = false) })
    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

}
