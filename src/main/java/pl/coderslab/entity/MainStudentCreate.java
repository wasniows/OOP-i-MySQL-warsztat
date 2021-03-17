package pl.coderslab.entity;

public class MainStudentCreate {

    public static void main(String[] args) {

        Student student = new Student("Krzysztof","Maliniak",11,"mailina@wp.pl","Warszawa, ul. Marszałkowska 1");
        StudentDao studentDao = new StudentDao();
        studentDao.create(student);


    }
}
