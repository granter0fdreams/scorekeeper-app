package org.launchcode.scorekeeperapp.models.data;

import org.launchcode.scorekeeperapp.models.Event;
import org.launchcode.scorekeeperapp.models.Scores;
import org.launchcode.scorekeeperapp.models.User;

import javax.validation.constraints.NotNull;

public class userEventScoreDTO {
    @NotNull
    private Event event;

    @NotNull
    private Scores score;

    @NotNull
    private User user;

    public userEventScoreDTO() {}

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Scores getScore() {
        return score;
    }

    public void setScore(Scores score) {
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
