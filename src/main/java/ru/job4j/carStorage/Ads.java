package ru.job4j.carStorage;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name="ads")
public class Ads implements Pts{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,  CascadeType.PERSIST})
    @JoinColumn(name="brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,  CascadeType.PERSIST})
    @JoinColumn(name="model_id")
    private Model model;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,  CascadeType.PERSIST})
    @JoinColumn(name="body_id")
    private Body body;

    @Column(name = "created")
    private Date created;

    @Column(name="active")
    private boolean active;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ads")
    private Set<Photo> photos;

    public Ads() {
    }

    public Ads(int id) {
        this.id = id;
    }

    @Override
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
/*
    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }
*/



}
