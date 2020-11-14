package pl.coderslab.entity;

public class MainDao {

    public static void main(String[] args) {

        //Test zapisu nowego usera do bazy
        UserDao userDao = new UserDao();
        User user = new User("Kamil", "kamil@wp.pl", "erggnurenguerg");
        userDao.create(user);

    }

}
