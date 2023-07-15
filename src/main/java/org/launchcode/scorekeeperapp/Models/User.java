package org.launchcode.scorekeeperapp.Models;

import javax.persistence.Entity;

@Entity
public class User extends AbstractEntity{

    private String email;

    public User() {
    }

    public User(String name, String email) {
        this.setName(name);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
