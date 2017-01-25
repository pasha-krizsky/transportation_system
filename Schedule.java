package com.company;

import java.util.HashMap;

/**
 * This class stores information about schedule. Each schedule has a map of bus numbers with
 * arrival times for each bus
 */
public class Schedule {
    /** Key - bus number, value - arrival time */
    private HashMap<Integer, DateAndTime> busNumberAndTime;

    /** Constructor */
    public Schedule() {
        busNumberAndTime = new HashMap<>();
    }

    /** Set bus and arrival time */
    public void setBusAndArrivalTime(Bus bus, DateAndTime dateAndTime) {
        busNumberAndTime.put(bus.getBusNumber(), dateAndTime);
    }

    /** Get arrival time for the bus */
    public DateAndTime getArrivalTime(Integer busNumber) {
        return busNumberAndTime.get(busNumber);
    }

    /** A string representation of an object */
    @Override
    public String toString() {
        String str = "";
        for (HashMap.Entry entry: busNumberAndTime.entrySet()) {
            str += "bus number: " + entry.getKey().toString() + "; date: " + entry.getValue().toString() + ";\n";
        }
        return str;
    }
}
