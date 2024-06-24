package com.example.wd.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DBConnectionPool {
    private static volatile DBConnectionPool instance;
    private BlockingQueue<Connection> connectionPool;
    private String url;
    private String username;
    private String password;
    private int poolSize = 10;

    private DBConnectionPool() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            this.url = properties.getProperty("jdbc.url");
            this.username = properties.getProperty("jdbc.username");
            this.password = properties.getProperty("jdbc.password");

            Class.forName(properties.getProperty("jdbc.driverClassName"));

            connectionPool = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                connectionPool.add(createNewConnection());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DBConnectionPool getInstance() {
        if (instance == null) {
            synchronized (DBConnectionPool.class) {
                if (instance == null) {
                    instance = new DBConnectionPool();
                }
            }
        }
        return instance;
    }

    private Connection createNewConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public Connection getConnection() {
        try {
            return connectionPool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            connectionPool.offer(connection);
        }
    }
}