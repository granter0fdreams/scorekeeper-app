package org.launchcode.scorekeeperapp.models;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Event extends AbstractEntity{

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Scores> scores = new ArrayList<>();

    private String eventName;

    @Positive
    @Digits(integer = 5, fraction = 0, message = "Please enter an integer larger than 0.")
    private Integer holes;
    public Event(){}
    public Event(User user, List<Scores> scores, String eventName, Integer holes) {
        super();
        this.user = user;
        this.scores = scores;
        this.eventName = eventName;
        this.holes = holes;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getHoles() {
        return holes;
    }

    public void setHoles(Integer holes) {
        this.holes = holes;
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

