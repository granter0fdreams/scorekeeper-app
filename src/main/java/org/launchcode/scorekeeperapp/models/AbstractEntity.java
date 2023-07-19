package org.launchcode.scorekeeperapp.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntity {
//An abstract class to use when making players, administrators and potentially even tournaments.
    @Id
    @GeneratedValue()
    private int id;
    //The object id will be the primary way to differentiate players/admins/tournaments
    //in the database.

    public int getId() {
        return id;
    }

    private HashMap<Integer, Integer> holesPars;

    private HashMap<Integer, Integer> holesScores;

    //A couple functions to set the par and score for a hole in the above hashmaps.  I feel like these might be used in multiple objects
    //so I am putting them here.
    public void setHolePar(Integer hole, Integer par){
        holesPars.put(hole, par);
    }

    public void setHolesScores(Integer hole, Integer score){
        holesScores.put(hole, score);
    }

    public HashMap<Integer, Integer> getHolesPars() {
        return holesPars;
    }

    public HashMap<Integer, Integer> getHolesScores() {
        return holesScores;
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
