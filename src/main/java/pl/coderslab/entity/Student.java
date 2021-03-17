package pl.coderslab.entity;

public class Student {
    private int id;
    private String name;
    private String lastName;
    private String address;
    private String email;
    private int number_indeks;

    public Student(String name, String lastName, int number_indeks, String email, String address) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.number_indeks = number_indeks;
    }

    public Student() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public int getNumber_indeks() {
        return number_indeks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumber_indeks(int number_indeks) {
        this.number_indeks = number_indeks;
    }
}
