package ru.job4j.carStorage;


import com.sun.javafx.geom.transform.Identity;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.omg.CORBA.portable.IDLEntity;

import javax.persistence.*;

@Entity
@Table(name="engine")
public class Engine implements Pts{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
