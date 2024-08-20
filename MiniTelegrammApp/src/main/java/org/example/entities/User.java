package org.example.entities;

public class User {
    private static long idCounter = 0;

    private long id;
    private String login;
    private String password;
    private String phone;

    public User(String login, String password, String phone) {
        this.id = ++idCounter;
        this.login = login;
        this.password = password;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public static void setIdCounter(long idCounter) {
        User.idCounter = idCounter;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        System.out.println("--------------------------------------------------------------------------------------------");
        return "Пользователь {" +
                "\n           Идентификатор = " + id +
                "\n           Логин = " + login + '\'' +
                "\n           Номер телефона = " + phone + '\'' +
                '}';
    }
}
