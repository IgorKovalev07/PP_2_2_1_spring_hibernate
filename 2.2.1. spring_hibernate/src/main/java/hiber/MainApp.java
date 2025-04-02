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

        Car test = context.getBean(Car.class);
        test.setModel("Tesla");
        test.setSeries(2020);
        Car test2 = context.getBean(Car.class);
        test2.setModel("BMW");
        test2.setSeries(2023);

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

        String model = "Tesla";
        int series = 2020;
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
