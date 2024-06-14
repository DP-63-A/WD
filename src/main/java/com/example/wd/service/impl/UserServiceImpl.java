/*package com.example.wd.service.impl;

import com.example.wd.dao.UserDao;
import com.example.wd.dao.impl.UserDaoImpl;
import com.example.wd.entity.User;
import com.example.wd.service.UserService;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = new UserServiceImpl();
    private UserDao userDao = new UserDaoImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean authentificate(String login, String password) {
        return userDao.authentificate(login, password);
    }

    @Override
    public boolean register(User user) {
        return userDao.insert(user);
    }
}*/
package com.example.wd.service.impl;

import com.example.wd.dao.UserDao;
import com.example.wd.dao.impl.UserDaoImpl;
import com.example.wd.entity.User;
import com.example.wd.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        logger.debug("Authenticating user: {}", login);
        boolean result = userDao.authentificate(login, password);
        if (result) {
            logger.info("User authenticated: {}", login);
        } else {
            logger.warn("Failed authentication attempt for user: {}", login);
        }
        return result;
    }

    @Override
    public boolean register(User user) {
        return false;
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
