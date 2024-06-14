package com.example.wd.dao;

import com.example.wd.entity.User;

public interface UserDao {
    boolean authentificate(String login, String password);

    boolean insert(User user);
}