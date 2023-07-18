package org.launchcode.scorekeeperapp.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntity {
//An abstract class to use when making players, administrators and potentially even tournaments.
    @Id
    @GeneratedValue
    private int id;
    //The object id will be the primary way to differentiate players/admins/tournaments
    //in the database.

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AbstractEntity abEnt = (AbstractEntity) obj;
        return id == abEnt.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}