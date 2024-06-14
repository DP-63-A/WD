package com.example.wd.service;

import com.example.wd.entity.User;

public interface UserService {
    boolean authentificate(String login, String password);
    boolean register(User user);

    boolean addUser(User user);
}
