package ru.job4j.carStorage;

import javax.persistence.*;

@Entity
@Table(name="driver")
public class Driver implements Pts{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Driver() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
