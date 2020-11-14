package pl.coderslab.entity;

public class MainDaoUpdate {

    public static void main(String[] args) {

        UserDao userDao = new UserDao();
        User userToUpdate = userDao.read(3);
        //userToUpdate.setUserName("Krzysztof Nowak");
        userToUpdate.setEmail("kn@Krosno.pl");
        //userToUpdate.setPassword("dasdfflka;slkjdf");
        userDao.update(userToUpdate);

    }
}
