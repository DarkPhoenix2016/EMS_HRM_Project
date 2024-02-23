package main.models;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {

    public static Connection connect() {
        try {
            // Read database information from the file
            InputStream inputStream = MySqlConnection.class.getResourceAsStream("/main/models/dbInfo.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));


            String url = reader.readLine();
            String username = reader.readLine();
            String password = reader.readLine();
            password = "";
            String database = reader.readLine();

            reader.close();

            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create the connection string
            String connectionString = "jdbc:mysql://" + url + "/" + database;

            // Establish the connection
            Connection connection = DriverManager.getConnection(connectionString, username, password);

            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
