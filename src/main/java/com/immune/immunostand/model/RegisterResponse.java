package com.immune.immunostand.model;

/**
 * Created by Ayodeji.Agboola on 3/27/2017.
 */
public class RegisterResponse {

    private User user;

    public RegisterResponse(User user){
        this.user=user;
    }
    public User getUser(){
        return user;
    }

}
