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

        User user1 = new User("Alex", "Bishop", "bishop@gmail.com");
        User user2 = new User("Kate", "White", "white@gmail.com");
        Car car1 = new Car("BMW", 5);
        Car car2 = new Car("Mers", 3);
        user1.setCar(car1);
        user2.setCar(car2);
        userService.add(user1);
        userService.add(user2);

        User user11 = userService.getUserByCarModelAndCarSeries("BMW", 5);
        User user22 = userService.getUserByCarModelAndCarSeries("Mers", 3);
        System.out.println(user11);
        System.out.println(user22);

        context.close();
    }
}
