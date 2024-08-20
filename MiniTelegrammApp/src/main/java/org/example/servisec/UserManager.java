package org.example.servisec;

import org.example.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users = new ArrayList<>();
    
    public User registerUser(String login,String password, String phone) {
        User user = findUserByLogin(login);
        if (user == null) {
            user = new User(login, password, phone);
            users.add(user);
            return  user;
        }
        return null;
    }

    public User authenticateUser(String login,String password) {
        for(User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public User findUserByLogin (String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        return users;
    };
}
