package ru.job4j.carStorage;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
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
        HistoryOwner that = (HistoryOwner) o;
        return id == that.id &&
                driverId == that.driverId &&
                carId == that.carId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, driverId, carId);
    }
}
