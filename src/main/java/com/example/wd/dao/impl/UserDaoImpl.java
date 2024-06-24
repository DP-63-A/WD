package com.example.wd.dao.impl;

import com.example.wd.dao.BaseDao;
import com.example.wd.dao.UserDao;
import com.example.wd.entity.User;
import com.example.wd.pool.DBConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public boolean insert(User user) {
        logger.debug("Inserting user: {}", user.getLogin());
        String sql = "INSERT INTO users (first_name, last_name, login, password, email, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getLastName());
                statement.setString(3, user.getLogin());
                statement.setString(4, user.getPassword());
                statement.setString(5, user.getEmail());
                statement.setString(6, user.getPhone());
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    logger.info("User inserted successfully: {}", user.getLogin());
                    return true;
                } else {
                    logger.warn("No rows inserted for user: {}", user.getLogin());
                }
            }
        } catch (SQLException e) {
            logger.error("Error inserting user: {}", user.getLogin(), e);
        } finally {
            if (connection != null) {
                DBConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public boolean deleteByLogin(String login) {
        String sql = "DELETE FROM users WHERE login = ?";
        try (Connection connection = DBConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("Error deleting user with login: " + login, e);
            return false;
        }
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
        logger.debug("Authentificating user: {}", login);
        String sql = "SELECT COUNT(*) FROM users WHERE login = ? AND password = ?";
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, login);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    logger.info("User authentificated successfully: {}", login);
                    return true;
                }
            }
        } catch (SQLException e) {
            logger.error("Error authentificating user: {}", login, e);
        } finally {
            if (connection != null) {
                DBConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return false;
    }
}