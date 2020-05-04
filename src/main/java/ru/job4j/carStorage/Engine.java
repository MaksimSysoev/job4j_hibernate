package ru.job4j.carStorage;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

public class Engine implements Pts{

    @OneToMany(mappedBy = "engine", cascade = CascadeType.ALL)
    @JoinColumn(name="id")
    private int id;

    public Engine() {
    }

    public Engine(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
