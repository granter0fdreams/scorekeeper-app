package org.launchcode.scorekeeperapp.Models.Data;


import org.launchcode.scorekeeperapp.Models.User;
import org.launchcode.scorekeeperapp.Models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TestRepo extends CrudRepository<Event, Integer> {

}