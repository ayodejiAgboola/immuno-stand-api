package com.immune.immunostand.model;

/**
 * Created by Ayodeji.Agboola on 3/27/2017.
 */
public class LoginResponse {
    private User user;

    public LoginResponse(User user){
        this.user=user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
