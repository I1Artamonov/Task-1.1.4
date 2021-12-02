package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS the_film_starred " +
                    "(id INT(2)  PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(10), last_name VARCHAR(20), age INT(3)) AUTO_INCREMENT=1;");
        } catch (SQLException createT) {
            createT.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS the_film_starred");
        } catch (SQLException dropT) {
            dropT.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     ("INSERT INTO the_film_starred (name, last_name, age) VALUES(?,?,?);")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException saveU) {
            saveU.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM the_film_starred WHERE id=?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }catch (SQLException removeU) {
            removeU.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()){
            ResultSet result = statement.executeQuery("SELECT * FROM the_film_starred");
            while (result.next()) {
                User user = new User(result.getString(2), result.getString(3), result.getByte(4));
                user.setId(result.getLong(1));
                users.add(user);
            }
        }catch (SQLException getUs) {
            getUs.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM the_film_starred")){
            statement.executeUpdate();
        } catch (SQLException cleanT) {
            cleanT.printStackTrace();
        }
    }
}
