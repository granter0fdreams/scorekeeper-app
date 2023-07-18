package org.launchcode.scorekeeperapp.models.data;

import org.launchcode.scorekeeperapp.models.Scores;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ScoreRepository extends CrudRepository<Scores,Integer> {
}
