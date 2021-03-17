package pl.coderslab.entity;

import pl.coderslab.DbUtil;

import java.sql.*;

public class StudentDao {
    private static final String CREATE_STUDENT_QUERY = "INSERT INTO students(name, lastName, number_indeks, email, address) VALUES (?, ?, ?, ?, ?)";
    private static final String READ_STUDENT_QUERY = "SELECT * FROM students WHERE id = ?";
    private static final String UPDATE_STUDENT_QUERY = "UPDATE students SET name = ?, lastName = ?, number_indeks = ?, email = ?, address = ? WHERE id = ?";
    private static final String DELETE_STUDENT_QUERY = "DELETE FROM students WHERE id = ?";

    //Zapisywanie nowego studenta do bazy student_ex
    public Student create(Student student) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(CREATE_STUDENT_QUERY, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getLastName());
            stmt.setInt(3, student.getNumber_indeks());
            stmt.setString(4, student.getEmail());
            stmt.setString(5,student.getAddress());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                student.setId(rs.getInt(1));
            }
            System.out.println("Zapisano");
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    //Odczytywanie studenta z bazy student_ex
    public Student read(int studentId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(READ_STUDENT_QUERY);
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Student student= new Student(rs.getString("name"),rs.getString("lastName"), rs.getInt("number_indeks") ,rs.getString("email"), rs.getString("address"));
                student.setId(rs.getInt("id"));
                return student;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Modyfikacja istniejącego studenta w bazie student_ex
    public void update(Student student) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_QUERY);
            statement.setString(1, student.getName());
            statement.setString(2, student.getLastName());
            statement.setInt(3,student.getNumber_indeks());
            statement.setString(4, student.getEmail());
            statement.setString(5, student.getAddress());
            statement.setInt(6, student.getId());
            statement.executeUpdate();
            System.out.println("Dane studenta zostały zmienione");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Usuwanie studenta z bazy student_ex
    public void delete(int studentId) {
        try (Connection connection = DbUtil.getConnection()) {
            StudentDao studentDao = new StudentDao();
            if (studentDao.read(studentId) != null) {
                PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_QUERY);
                statement.setInt(1, studentId);
                statement.executeUpdate();
                System.out.println("Student " + " o ID " + studentId + " został usunięty");
            } else {
                System.out.println("nie ma w bazie usera o ID " + studentId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
