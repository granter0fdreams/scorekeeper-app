package org.launchcode.scorekeeperapp.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Event extends AbstractEntity{

    @ManyToOne
    private String name;

    public String getName() {
        return name;
    }

    public void setTournamentName(String name) {
        this.name = name;
    }
}
