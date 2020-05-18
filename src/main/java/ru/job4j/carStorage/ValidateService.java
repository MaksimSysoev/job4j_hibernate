package ru.job4j.carStorage;

import javax.jws.WebParam;
import java.util.Date;
import java.util.List;
import java.util.Set;

public final class ValidateService {

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
