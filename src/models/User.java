package models;

public abstract class User {
    protected String username;
    protected int userID;
    protected String userPassword; // Will match "admin123" for Admin role check

    public User(String username, int userID, String userPassword) {
        this.username = username;
        this.userID = userID;
        this.userPassword = userPassword;
    }

    // Basic getters
    public String getUsername() { return username; }
    public int getUserID() { return userID; }
    public String getUserPassword() { return userPassword; }
}