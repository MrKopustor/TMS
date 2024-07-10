package com.teachmeskills.hw_35.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDriverManager {
    private static PostgresDriverManager instance;
    private static String URL = "jdbc:postgresql://localhost:5432/tms";
    private static String USERNAME = "postgres";
    private static String PASSWORD = "1111";

    private PostgresDriverManager() {
        init();
    }

    private void init() {
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            System.out.println("Exception loading driver...");
        }
    }

    public static PostgresDriverManager getInstance() {
        if (instance == null) {
            instance = new PostgresDriverManager();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
