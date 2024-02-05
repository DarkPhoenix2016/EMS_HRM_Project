package main.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {

    public static Connection connect() {
        try {
            // Get the absolute path to the class
            URL classUrl = MySqlConnection.class.getProtectionDomain().getCodeSource().getLocation();
            Path classPath = Paths.get(classUrl.toURI()).getParent();

            // Construct the absolute path to the text file
            String filePath = classPath+"/src/main/models/dbInfo.txt".toString();

            // Read database information from the file
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

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
