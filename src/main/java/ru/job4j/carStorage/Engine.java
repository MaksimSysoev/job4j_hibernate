package ru.job4j.carStorage;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="engine")
public class Engine implements Pts{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    private Set<Engine> engines;

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

    public Set<Engine> getEngines() {
        return engines;
    }

    public void setEngines(Set<Engine> engines) {
        this.engines = engines;
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
        Engine engine = (Engine) o;
        return id == engine.id &&
                Objects.equals(engines, engine.engines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, engines);
    }
}
