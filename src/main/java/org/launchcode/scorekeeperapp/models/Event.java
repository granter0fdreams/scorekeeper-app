package org.launchcode.scorekeeperapp.models;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event extends AbstractEntity{

    private String name;

    @Positive
    @Digits(integer = 5, fraction = 0, message = "Please enter an integer larger than 0.")
    private Integer holes;

    //An array of players, no need for a hashmap with (name, id) because the user object will provide a unique ID.
//    @ManyToMany(mappedBy = "user")
//    //@ElementCollection
//    private List<User> players = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHoles() {
        return holes;
    }

    public void setHoles(Integer holes) {
        this.holes = holes;
    }

    public Event(String name, Integer holes, List<User> players) {
        this.name = name;
        this.holes = holes;
        //this.players = players;
    }
    public Event(){}
}
