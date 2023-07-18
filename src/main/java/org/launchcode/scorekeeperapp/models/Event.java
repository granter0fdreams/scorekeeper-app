package org.launchcode.scorekeeperapp.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Event extends AbstractEntity{

    //admin is the person who creates the event.
    private User admin;

    private String name;

    //An array of players, no need for a hashmap with (name, id) because the user object will provide a unique ID.
    private ArrayList<User> players;

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<User> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<User> players) {
        this.players = players;
    }

    public Event() {
    }
}
