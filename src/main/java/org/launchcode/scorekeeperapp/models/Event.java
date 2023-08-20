package org.launchcode.scorekeeperapp.models;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Event extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    private List<Scores> scores = new ArrayList<>();

    @NotNull
    private String eventName;

    @NotNull
    private Integer numberOfHoles;
    public Event(){}
    public Event(User user, List<Scores> scores, String eventName, Integer numberOfHoles) {
        super();
        this.user = user;
        this.scores = scores;
        this.eventName = eventName;
        this.numberOfHoles = numberOfHoles;
    }

    public Event(String eventName, Integer numberOfHoles) {
        this.eventName = eventName;
        this.numberOfHoles = numberOfHoles;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getNumberOfHoles() {
        return numberOfHoles;
    }

    public void setNumberOfHoles(Integer numberOfHoles){
        this.numberOfHoles = numberOfHoles;
    }


    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Scores> getScores() {
        return scores;
    }

    public void setScores(List<Scores> scores) {
        this.scores = scores;
    }

    private boolean closed = false;


}

