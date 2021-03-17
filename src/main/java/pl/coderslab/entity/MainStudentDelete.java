package pl.coderslab.entity;

public class MainStudentDelete {

    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();
        studentDao.delete(1);
    }



}
