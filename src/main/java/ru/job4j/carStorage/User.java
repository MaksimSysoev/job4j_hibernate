package ru.job4j.carStorage;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User implements Pts{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    public User() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
