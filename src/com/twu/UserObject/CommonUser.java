package com.twu.UserObject;

public class CommonUser extends User{

    public CommonUser(String userName, String password) {
        super(userName,password);
        this.setUserType(2);
    }
}
