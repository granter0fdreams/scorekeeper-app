package org.launchcode.scorekeeperapp.models;

import java.util.HashMap;

public class Score extends AbstractEntity{
    private Integer userID;
    private Integer eventID;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public Score() {
    }

}
