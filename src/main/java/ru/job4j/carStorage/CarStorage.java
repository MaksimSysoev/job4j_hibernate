package ru.job4j.carStorage;

public class CarStorage {

    public CarStorage() {
    }

    public static void main(String[] args) {
        ru.job4j.carStorage.Service service = new Service();
       // Engine engine = new Engine();
       // engine.setType("k7m");
      //  service.add(engine);

        Car car = new Car();
        car.setEngine_id(new Engine(5));
        service.add(car);

    }

}
