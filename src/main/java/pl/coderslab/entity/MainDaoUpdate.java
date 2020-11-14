package pl.coderslab.entity;

public class MainDaoUpdate {

    public static void main(String[] args) {

        UserDao userDao = new UserDao();
        User userToUpdate = userDao.read(1);
        userToUpdate.setUserName("Krzysztof Nowak");
        userToUpdate.setEmail("kn@wpl.pl");
        userToUpdate.setPassword("dasdfflka;slkjdf");
        userDao.update(userToUpdate);

    }
}
