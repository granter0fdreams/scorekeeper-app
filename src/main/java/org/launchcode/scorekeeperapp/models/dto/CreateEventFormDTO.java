package org.launchcode.scorekeeperapp.models.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CreateEventFormDTO {
    @NotNull
    @NotBlank
    private String eventName;

    @NotNull
    @NotBlank
    @Positive
    @Digits(integer = 5, fraction = 0, message = "Please enter an integer larger than 0.")
    private Integer numberOfHoles;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getNumberOfHoles() {
        return numberOfHoles;
    }

    public void setHoles(Integer numberOfHoles) {
        this.numberOfHoles = numberOfHoles;
    }
}
