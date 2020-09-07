package com.twu.UserObject;

public class AdminUser extends User {

    public AdminUser(String userName, String password){
        super(userName,password);
        this.setUserType(1);
    }


}
