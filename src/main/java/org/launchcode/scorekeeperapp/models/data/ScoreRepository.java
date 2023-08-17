package org.launchcode.scorekeeperapp.models.data;

import org.launchcode.scorekeeperapp.models.Scores;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface ScoreRepository extends CrudRepository<Scores,Integer> {
    ArrayList<Scores> findByEventId(Integer eventId);
    List<Scores> findByEventIdAndUserId(Integer eventId, Integer userId);

}
