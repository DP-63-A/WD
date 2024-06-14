package com.example.wd.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.util.concurrent.locks.ReentrantLock;

public class DBConnectionPool {
    private static volatile DBConnectionPool instance;
    private static volatile Connection connection;
    private static final ReentrantLock lock = new ReentrantLock();

    private String url;
    private String username;
    private String password;

    private DBConnectionPool() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            this.url = properties.getProperty("jdbc.url");
            this.username = properties.getProperty("jdbc.username");
            this.password = properties.getProperty("jdbc.password");

            Class.forName(properties.getProperty("jdbc.driverClassName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DBConnectionPool getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new DBConnectionPool();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            lock.lock();
            try {
                if (connection == null) {
                    connection = DriverManager.getConnection(url, username, password);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
        return connection;
    }
}