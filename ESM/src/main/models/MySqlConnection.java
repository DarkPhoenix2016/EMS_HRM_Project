package main.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    public static Connection connect() {
        try {
            // Read database information from the file
            InputStream inputStream = MySqlConnection.class.getResourceAsStream("/main/models/dbInfo.txt");
            String url;
            String username;
            String password;
            String database;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                url = reader.readLine();
                username = reader.readLine();
                password = reader.readLine();
                database = reader.readLine();
            }

            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create the connection string
            String connectionString = "jdbc:mysql://" + url + "/" + database;
            // Establish the connection

            return DriverManager.getConnection(connectionString, username, password);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            return null;
        }
    }
}
