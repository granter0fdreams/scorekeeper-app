package org.launchcode.scorekeeperapp.models.data;

import org.springframework.data.repository.CrudRepository;
import org.launchcode.scorekeeperapp.models.Event;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event,Integer> {
}
