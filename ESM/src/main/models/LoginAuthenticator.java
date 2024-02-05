package main.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;

import main.controllers.SessionManager;

public class LoginAuthenticator {

    public static String authenticate(String username, String password, String loginType) {
        // Validate username format
    	if (!username.matches("^[a-zA-Z0-9_]+$")) {
    	    return "Invalid username format. \n Only letters, numbers, and underscores are allowed.";
    	}

        try {
            Connection connection = MySqlConnection.connect();

            if (connection == null) {
                return "Failed to connect to the database.";
            }

            String tableName = (loginType.equals("Admin")) ? "00_admins" : "02_02_employee_accounts";

            String sql = (loginType.equals("Admin")) ? "SELECT * FROM " + tableName + " WHERE username = ?" : "SELECT * FROM " + tableName + " WHERE username = ? AND status = 'active'";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            
            //System.out.print(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the user exists
            if (resultSet.next()) {
                // Validate the password
                String storedHash = resultSet.getString("password");
                if (BCrypt.checkpw(password, storedHash)) {
                    // Authentication successful
                	 SessionManager.getInstance().setLoggedInUsername(username);
                	 SessionManager.getInstance().setLoggedInRole(loginType);              	 
                    return "Login successful!";
                } else {
                    // Invalid password
                    return "Invalid password.";
                }
            } else {
                // User not found or inactive
                return "Invalid username or user account is deactivated.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred during authentication.";
        }
    }

}
