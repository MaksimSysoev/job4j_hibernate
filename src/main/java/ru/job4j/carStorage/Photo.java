package ru.job4j.carStorage;

import javax.persistence.*;

@Entity
@Table(name="photo")
public class Photo implements Pts{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ads_id")
    private Ads ads;

    public Photo() {

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


    public Ads getAds() {
        return ads;
    }

    public void setAds(Ads ads) {
        this.ads = ads;
    }

}
