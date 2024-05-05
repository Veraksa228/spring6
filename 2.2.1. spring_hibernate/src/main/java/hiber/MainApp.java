package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        User user1 = new User("Alex", "Evanov", "asd@gmail.com");
        User user2 = new User("Sasha", "Karpov", "qwef@gmail.com");
        User user3 = new User("Masha", "Kotov", "zxc@gmail.com");

        Car car1 = new Car("Tesla", 11);
        Car car2 = new Car("BMW", 1);
        Car car3 = new Car("AUDI", 2);
        userService.addCar(car1);
        userService.addCar(car2);
        userService.addCar(car3);
        user1.setCar(car1); // Tesla
        user2.setCar(car2); // BMW
        user3.setCar(car3); // AUDI
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);

        List<User> users = userService.getUserByCar("BMW", 1);
        for (User user : users) {
            System.out.println(user.getFirstName());
            System.out.println(user.getLastName());
            System.out.println(user.getEmail());
            System.out.println(user.getCar().toString());
        }

        context.close();
    }
}
