package org.launchcode.scorekeeperapp.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Event extends AbstractEntity{

    private String tournamentName;

    public Event() {
    }

    public Event(String tournamentName) {
        super();
        this.tournamentName = tournamentName;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }
}
