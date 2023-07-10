package org.launchcode.scorekeeperapp.Models;

public class User extends AbstractEntity{

    private String email;

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

}
