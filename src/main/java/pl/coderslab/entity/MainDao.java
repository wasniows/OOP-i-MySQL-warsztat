package pl.coderslab.entity;

public class MainDao {

    public static void main(String[] args) {

        //Test zapisu nowego usera do bazy
        UserDao userDao = new UserDao();
        User user = new User();
        user.setUserName("Marek");
        user.setEmail("marek@onet.pl");
        user.setPassword("asjdfasdff;alAAkfj");
        userDao.create(user);

    }

}
