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

        Car test = new Car("Tesla", 2021);
        Car test2 = new Car("Tesla", 2022);
        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
        userService.add(new User("User5", "Lastname5", "user5@mail.ru", test));
        userService.add(new User("User6", "Lastname6", "user6@mail.ru", test2));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }
        System.out.println();
        String model = "Tesla";
        int series = 2021;
        try {
            User user = userService.getUserHasCar(model, series);
            System.out.println("User found: " + user.getFirstName() + " with car: " + user.getCar().getModel()
                    + " with series: " + user.getCar().getSeries());
        } catch (Exception e) {
            System.out.println("User not found for car model: " + model + " and series: " + series);
        }

        context.close();
    }
}
