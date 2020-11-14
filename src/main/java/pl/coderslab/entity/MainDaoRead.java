package pl.coderslab.entity;

public class MainDaoRead {

    public static void main(String[] args) {

        //Test odczytu usera z bazy workshop2
        UserDao userDao = new UserDao();
        User user = userDao.read(3);

        System.out.println(user);

    }

}
