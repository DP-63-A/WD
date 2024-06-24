package com.example.wd.service;

import com.example.wd.entity.User;

import java.util.List;

public interface UserService {
    boolean authentificate(String login, String password);

    boolean register(User user);

    boolean addUser(User user);

    List<User> findAllUsers();
    boolean deleteUserByLogin(String login);
}
