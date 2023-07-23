package org.launchcode.scorekeeperapp.models;

import javax.persistence.Entity;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Positive;

@Entity
public class Event extends AbstractEntity{

    private String name;

    @Positive
    @Digits(integer = 5, fraction = 0, message = "Please enter an integer larger than 0.")
    private Integer holes;

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

    public Event(String name) {
        this.name = name;
    }
    public Event(){}
}
