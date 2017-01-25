package com.company;

import java.util.HashSet;

/**
 * Bus has a bus number, sets of stations and passengers and amount of seats.
 * This class represents a simple way to store all information about buses.
 * It also provides a set of methods to define the behavior of bus objects
 */
public class Bus {
    /** An identifier of bus object */
    private int busNumber;
    /** All the stations visited by the bus */
    private HashSet<Station> stations;
    /** All passengers registered to the bus */
    private HashSet<Passenger> passengers;
    /** Amount of seats in the bus */
    private int allSeats;
    /** Amount of free seats */
    private int freeSeats;

    /**
     * Constructor
     * @param busNumber - the number of the bus
     * @param stations - set of stations for the bus
     * @param allSeats - amount of seats of the bus
     */
    public Bus(int busNumber, HashSet<Station> stations, int allSeats) {
        this.busNumber = busNumber;
        this.stations = stations;
        this.allSeats = allSeats;
        this.freeSeats = allSeats;

        // Empty set of passengers
        this.passengers = new HashSet<>();
    }

    /** Get bus number */
    public int getBusNumber() {
        return busNumber;
    }

    /** Get set of passengers registered to the bus */
    public HashSet<Passenger> getPassengers() {
        return passengers;
    }

    /** Get set of stations */
    public HashSet<Station> getStations() {
        return stations;
    }

    /** Get amount of seats in the bus */
    public int getAllSeats() {
        return allSeats;
    }

    /** Get amount of free seats in the bus */
    public int getFreeSeats() {
        return freeSeats;
    }

    /**
     * Add new passenger to the bus
     * @param passenger - new passenger
     */
    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
        freeSeats--;
    }
}
