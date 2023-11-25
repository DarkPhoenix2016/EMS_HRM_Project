package main.controllers;

public class SessionManager {
    private static SessionManager instance;
    private String loggedInUsername;
    private String loggedInRole;

    private SessionManager() {
        // private constructor to prevent instantiation
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;
    }
    
    public void setLoggedInRole(String role) {
        this.loggedInRole = role;
    }

    
    public String getLoggedInUsername() {
        return loggedInUsername;
    }
    public String getLoggedInRole() {
    	return loggedInRole;
    }

    public void clearSession() {
        loggedInUsername = null;
        loggedInRole =null;
    }
}

