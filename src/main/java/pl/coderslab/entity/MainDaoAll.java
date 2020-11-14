package pl.coderslab.entity;

public class MainDaoAll {

    public static void main(String[] args) {

        UserDao userDao = new UserDao();
        User[] all = userDao.findAll();

        for (User u : all) {
            System.out.println(u);
        }
    }
 }
