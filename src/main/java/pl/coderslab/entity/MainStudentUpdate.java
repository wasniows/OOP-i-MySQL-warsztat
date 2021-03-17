package pl.coderslab.entity;

public class MainStudentUpdate {

    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();
        Student studentToUpdate = new Student();
        studentToUpdate = studentDao.read(2);
        studentToUpdate.setAddress("Kraków, Rynek 4");
        studentDao.update(studentToUpdate);



    }

}
