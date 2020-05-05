package ru.job4j.carStorage;

public class CarStorage {

    public CarStorage() {
    }

    public static void main(String[] args) {
        ru.job4j.carStorage.Service service = new Service();
       // Engine engine = new Engine();
       // service.add(engine);
       // service.add(engine);
       // service.add(engine);

       Car car = new Car();
       car.setEngine_id(new Engine(8));
       service.add(car);
        car.setEngine_id(new Engine(9));
        service.add(car);
        car.setEngine_id(new Engine(10));
        service.add(car);


        Driver driver = new Driver();
        service.add(driver);
        service.add(driver);
        service.add(driver);


    }

}
