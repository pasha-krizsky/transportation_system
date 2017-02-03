package com.forms;

import com.objects.Bus;
import com.objects.Station;

import java.sql.Time;
import java.util.List;
import java.util.Date;

/**
 * This class defines a model for client side. (MVC)
 */
public class ClientForm {

    /** Name of passenger */
    private String name;
    /** Surname of passenger */
    private String surname;
    /** Date of birth of passenger */
    private Date dateOfBirth;
    /** List of all buses */
    private List<Bus> buses;
    /** List of all stations */
    private List<Station> stations;
    /** Min time input */
    private Time minTime;
    /** Max time input */
    private Time maxTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public Time getMinTime() {
        return minTime;
    }

    public void setMinTime(Time minTime) {
        this.minTime = minTime;
    }

    public Time getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Time maxTime) {
        this.maxTime = maxTime;
    }
}