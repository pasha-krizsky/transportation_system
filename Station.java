package com.company;

/**
 * This class stores information about station. Each station has a name and a schedule
 */
public class Station {
    // The name of the station
    private String name;
    // The schedule for the station
    private Schedule schedule;

    // Constructor
    public Station(String name, Schedule schedule) {
        this.name = name;
        this.schedule = schedule;
    }

    // Access methods
    public String getName() {
        return name;
    }

    public Schedule getSchedule() {
        return schedule;
    }
}
