package ru.job4j.carStorage;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="body")
public class Body implements Pts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "body")
    private Set<Model> models;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "body")
    private Set<Ads> ads;

    public Body() {

    }

    public Body(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<Model> getModels() {
        return models;
    }

    public void setModels(Set<Model> model) {
        this.models = model;
    }

    public Set<Ads> getAds() {
        return ads;
    }

    public void setAds(Set<Ads> ads) {
        this.ads = ads;
    }

    @Override
    public Brand getBrand() {
        return null;
    }

    @Override
    public Body getBody() {
        return null;
    }

    @Override
    public Model getModel() {
        return null;
    }
}
