package org.launchcode.scorekeeperapp.models.data;

import org.launchcode.scorekeeperapp.models.Event;
import org.launchcode.scorekeeperapp.models.Scores;
import org.launchcode.scorekeeperapp.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ScoreRepository extends CrudRepository<Scores,Integer> {
    //Scores findByEventId(String event_id);
    Scores findByEventId(int event_id);
}
