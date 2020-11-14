package pl.coderslab.entity;


import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.DbUtil;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.sql.*;
import java.util.Arrays;

public class UserDao {
    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";
    private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users";

    public static void main(String[] args) {

    }

    //Haszowanie hasła
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    //Zapisywanie nowego user-a do bazy workshop2
    public User create(User user) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, hashPassword(user.getPassword()));
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
            System.out.println("Zapisano");
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    //Odczytywanie usera z bazy workshop2
    public User read(int userId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getString("username"), rs.getString("email"), rs.getString("password"));
                user.setId(rs.getInt("id"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Modyfikacja istniejącego usera w bazie workshop2
    public void update(User user) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, this.hashPassword(user.getPassword()));
            statement.setInt(4, user.getId());
            statement.executeUpdate();
            System.out.println("Dane zostały zmienione");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Usuwanie usera z bazy workshop2
    public void delete(int usersId) {
        try (Connection connection = DbUtil.getConnection()) {
            UserDao userDao = new UserDao();
            if(userDao.read(usersId) != null){
                PreparedStatement statement = connection.prepareStatement(DELETE_USER_QUERY);
                statement.setInt(1, usersId);
                statement.executeUpdate();
                System.out.println("Usuer " + " o ID " + usersId + " został usunięty");
            } else {
                System.out.println("nie ma w bazie usera o ID "+usersId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Wczytywanie wszystkich userow z bazy workshop2
    public User[] findAll() {
        try (Connection connection = DbUtil.getConnection()) {
            User[] users = new User[0];
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS_QUERY);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId((rs.getInt("id")));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                users = addToArray(user, users);
            }
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = u;
        return tmpUsers;
    }
}

