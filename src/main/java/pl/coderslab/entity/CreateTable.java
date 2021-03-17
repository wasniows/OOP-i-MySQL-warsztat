package pl.coderslab.entity;

import pl.coderslab.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTable {

    private static final String QUERY1 = "CREATE TABLE students" +
            " (" +
            "id INT AUTO_INCREMENT, " +
            "name VARCHAR(20), " +
            "lastName VARCHAR(30), " +
            "number_indeks INT UNSIGNED UNIQUE, " +
            "email VARCHAR(50) UNIQUE, " +
            "address VARCHAR(100), " +
            "PRIMARY KEY (id));";
    private static final String QUERY2 = "CREATE TABLE exams" +
            " (" +
            "id INT UNSIGNED, " +
            "description VARCHAR(50), " +
            "PRIMARY KEY (id));";
    private static final String QUERY3 = "CREATE TABLE points_exams" +
            " (" +
            "id INT UNSIGNED AUTO_INCREMENT, " +
            "exam_id INT UNSIGNED, " +
            "points INT , " +
            "comment VARCHAR(100), " +
            "PRIMARY KEY (id), " +
            "FOREIGN KEY (exam_id) REFERENCES exams(id) ON DELETE CASCADE);";


    public static void main(String[] args) {

        try (Connection connection = DbUtil.getConnection()) {
            execute(connection, QUERY1);
            execute(connection, QUERY2);
            execute(connection, QUERY3);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void execute(Connection connection, String query) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}