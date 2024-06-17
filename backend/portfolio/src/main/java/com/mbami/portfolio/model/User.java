package com.mbami.portfolio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String fName;
    String lName;
    String email;
    String password; // TODO: hash this
    String authRole; // admin or guest
    String status; // active or inactive

    public User() {
    }

    public User(String fName, String lName, String email, String password, String authRole, String status) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
        this.authRole = authRole;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAuthRole() {
        return authRole;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthRole(String authRole) {
        this.authRole = authRole;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
