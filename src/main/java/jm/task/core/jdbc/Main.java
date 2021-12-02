package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Vasya", "Puking", (byte) 99);
        userService.saveUser("Volodya", "Putking", (byte) 69);
        userService.saveUser("Volondya", "Demordking", (byte) 96);
        userService.saveUser("And", "Other", (byte) 66);

        for (User user : userService.getAllUsers()) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
