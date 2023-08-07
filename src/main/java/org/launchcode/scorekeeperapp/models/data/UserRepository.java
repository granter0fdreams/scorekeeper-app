package org.launchcode.scorekeeperapp.models.data;

import org.launchcode.scorekeeperapp.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
    User findByUserId(int userId);
}