package com.softer.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String DEFAULT_URL =
            "jdbc:mysql://localhost:3306/consultant_manager?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    private final String url;
    private final String username;
    private final String password;

    public DatabaseConfig() {
        this.url = readSetting("DB_URL", DEFAULT_URL);
        this.username = readSetting("DB_USERNAME", "root");
        this.password = readSetting("DB_PASSWORD", "");
    }

    private String readSetting(String key, String defaultValue) {
        String value = System.getenv(key);
        System.out.println("Key: " + key);
        System.out.println("Value: " + value);
        return value == null || value.isBlank() ? defaultValue : value;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
