package ru.job4j.carStorage;

import java.util.List;

public class ValidateService {

    private static ValidateService instance = null;

    private final Service service = Service.getInstance();

    private ValidateService() {

    }

    public static synchronized ValidateService getInstance() {
        if (instance == null) {
            instance = new ValidateService();
        }
        return instance;
    }

    public void setInstance() {
        Service.getInstance();
    }

    public List<Pts> select(String table) {
        return service.select(table);
    }

    public void add(Pts pts) {
        service.add(pts);
    }

}
