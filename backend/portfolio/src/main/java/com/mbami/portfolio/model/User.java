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

}
