package com.example.wd.service.impl;

import com.example.wd.dao.UserDao;
import com.example.wd.dao.impl.UserDaoImpl;
import com.example.wd.entity.User;
import com.example.wd.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private static UserServiceImpl instance = new UserServiceImpl();
    private UserDao userDao = new UserDaoImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean authentificate(String login, String password) {
        logger.debug("Authentificating user: {}", login);
        boolean result = userDao.authentificate(login, password);
        if (result) {
            logger.info("User authentificated: {}", login);
        } else {
            logger.warn("Failed authentification attempt for user: {}", login);
        }
        return result;
    }

    @Override
    public boolean register(User user) {
        logger.debug("Registering user: {}", user.getLogin());
        return addUser(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }
    @Override
    public boolean deleteUserByLogin(String login) {
        return userDao.deleteByLogin(login);
    }
    @Override
    public boolean addUser(User user) {
        logger.debug("Adding user: {}", user.getLogin());
        boolean result = userDao.insert(user);
        if (result) {
            logger.info("User added successfully: {}", user.getLogin());
        } else {
            logger.warn("Failed to add user: {}", user.getLogin());
        }
        return result;
    }
}