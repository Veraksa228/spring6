package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    public void addCar(Car user);

    List<User> getUserByCar(String model, int series);
}
