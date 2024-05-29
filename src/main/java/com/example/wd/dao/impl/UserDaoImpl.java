package com.example.wd.dao.impl;

import com.example.wd.dao.BaseDao;
import com.example.wd.dao.UserDao;
import com.example.wd.entity.User;

import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

    @Override
    public boolean insert(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean authentificate(String login, String password) {
        return false;
    }


}

