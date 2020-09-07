package com.twu.UserObject;

public abstract class User {

    public int votes = 10;
    private String userName;
    private String password;
    private int userType;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public int getVotes() {
        return votes;
    }

    public void reduceVotes(int votes) {
        this.votes -= votes;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
