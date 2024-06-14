/*package com.example.wd.dao.impl;

import com.example.wd.dao.BaseDao;
import com.example.wd.dao.UserDao;
import com.example.wd.entity.User;
import com.example.wd.pool.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao { //maybeanerror
    private static final String INSERT_USER_SQL = "INSERT INTO users (first_name, last_name, login, password, email, phone) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String AUTHENTIFICATE_SQL = "SELECT * FROM users WHERE login = ? AND password = ?";

    @Override
    public boolean authentificate(String login, String password) {
        return false;
    }

    @Override
    public boolean insert(User user) {
        try (Connection connection = DBConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPhone());
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(User user) {
        // Реализуйте метод удаления
        return false;
    }

    @Override
    public List<User> findAll() {
        // Реализуйте метод поиска всех пользователей
        return null;
    }

    @Override
    public User update(User user) {
        // Реализуйте метод обновления пользователя
        return null;
    }

    @Override
    public boolean authenticate(String login, String password) {
        try (Connection connection = DBConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(AUTHENTIFICATE_SQL)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();  // Возвращает true, если в результате есть записи
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}*/
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
        String sql = "INSERT INTO users (first_name, last_name, login, password, email, phone) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
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
            }
        } catch (SQLException e) {
            logger.error("Error inserting user: {}", user.getLogin(), e);
        }
        return false;
    }

    @Override
    public boolean authentificate(String login, String password) {
        logger.debug("Authenticating user: {}", login);
        String sql = "SELECT COUNT(*) FROM users WHERE login = ? AND password = ?";
        try (Connection connection = DBConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                logger.info("User authenticated successfully: {}", login);
                return true;
            }
        } catch (SQLException e) {
            logger.error("Error authenticating user: {}", login, e);
        }
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


}