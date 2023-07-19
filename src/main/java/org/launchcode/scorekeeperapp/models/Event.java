package org.launchcode.scorekeeperapp.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
public class Event extends AbstractEntity{

    @Positive
    @Digits(integer = 5, fraction = 0, message = "Please enter an integer larger than 0.")
    private Integer holes;

    //admin is the person who creates the event.
//    @OneToMany
//    private User admin;

    private String name;

    //An array of players, no need for a hashmap with (name, id) because the user object will provide a unique ID.
//    @OneToMany(mappedBy = "Users")
//    private List<User> players = new ArrayList<>();

//    public User getAdmin() {
//        return admin;
//    }
//
//    public void setAdmin(User admin) {
//        this.admin = admin;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<User> getPlayers() {
//        return players;
//    }
//
//    public void setPlayers(List<User> players) {
//        this.players = players;
//    }

    public Integer getHoles() {
        return holes;
    }

    public void setHoles(Integer holes) {
        this.holes = holes;
    }

    public Event() {
    }
}
