package com.immune.immunostand.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Ayodeji.Agboola on 3/27/2017.
 */
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @Indexed(unique=true)
    private String username;
    private String password;
    @Indexed(unique=true)
    private String email;
    private String active;
    private String userType;
    private String hospitalId;
public User(String username, String password, String email, String active, String userType, String hospitalId){
    this.setUsername(username);
    this.setPassword(password);
    this.setEmail(email);
    this.setActive(active);
    this.setUserType(userType);
    this.setHospitalId(hospitalId);
}

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getActive() {
        return active;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }
}
