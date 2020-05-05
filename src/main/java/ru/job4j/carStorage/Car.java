package ru.job4j.carStorage;

import javax.persistence.*;


@Entity
@Table(name="car")
public class Car implements Pts{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private Engine engine;

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
}
