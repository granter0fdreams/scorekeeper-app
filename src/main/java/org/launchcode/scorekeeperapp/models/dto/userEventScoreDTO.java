package org.launchcode.scorekeeperapp.models.dto;

import org.launchcode.scorekeeperapp.models.Event;
import org.launchcode.scorekeeperapp.models.Scores;
import org.launchcode.scorekeeperapp.models.User;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class userEventScoreDTO {

    private List<Scores> scores = new ArrayList<>();

    public userEventScoreDTO(List<Scores> scores) {
        this.scores = scores;
    }
    public userEventScoreDTO(){}


    public void addScore(Scores score){
        this.scores.add(score);
    }

    public List<Scores> getScores() {
        return scores;
    }

    public void setScores(List<Scores> scores) {
        this.scores = scores;
    }

}
