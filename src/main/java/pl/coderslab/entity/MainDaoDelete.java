package pl.coderslab.entity;

public class MainDaoDelete {

    public static void main(String[] args) {

        UserDao userDao = new UserDao();
        userDao.delete(1);

    }
}
