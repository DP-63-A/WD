package com.example.wd.dao;

import com.example.wd.entity.User;

import java.util.List;

public interface UserDao {
    boolean insert(User user);
    boolean deleteByLogin(String login);
    List<User> findAll();
    User update(User user);
    boolean authentificate(String login, String password);
}