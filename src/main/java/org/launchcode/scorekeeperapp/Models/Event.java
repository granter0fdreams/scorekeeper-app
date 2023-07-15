package org.launchcode.scorekeeperapp.Models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.util.Date;
@Entity
public class Event extends AbstractEntity{
    @NotBlank(message = "Please name your event! 3-100 Characters.")
    @Size(min = 3, max = 100)
    private String name;
    @NotBlank(message = "Please enter a street address.")
    private String streetAddress;
    @NotBlank(message = "Please enter a city.")
    private String city;
    @NotBlank(message = "Please enter a state.")
    private String state;

    private String ZIPCode;


    private java.sql.Date date;

    private Time time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return ZIPCode;
    }

    public void setZipCode(String zipCode) {
        this.ZIPCode = zipCode;
    }

    public Date getDate() {
        return date;
    }


    public String getZIPCode() {
        return ZIPCode;
    }

    public void setZIPCode(String ZIPCode) {
        this.ZIPCode = ZIPCode;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Event() {
    }
}
