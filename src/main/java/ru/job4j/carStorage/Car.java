package ru.job4j.carStorage;

import javax.persistence.*;


@Entity
public class Car implements Pts{

    private int id;

    @ManyToOne
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"), insertable=false, updatable=false )
    private Engine engine_id;

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Engine getEngine_id() {
        return engine_id;
    }

    public void setEngine_id(Engine engine_id) {
        this.engine_id = engine_id;
    }
}
