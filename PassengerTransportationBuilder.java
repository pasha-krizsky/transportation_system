package com.company;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Abstract class to build the transportation system. Implementation of Builder pattern.
 * Construction is done in concrete builders
 */
public abstract class PassengerTransportationBuilder {
    /** Link to transportation system */
    protected PassengerTransportation passengerTransportation;
    /** Sets of stations */
    protected ArrayList<HashSet<Station>> stations;
    /** List of schedules */
    protected ArrayList<Schedule> schedules;

    /**
     * Get transportation system
     * @return created transportation system
     */
    public PassengerTransportation getSystem() {
        return passengerTransportation;
    }

    /**
     * Creates new transportation system.
     * The method is called first in Director (Creator class)
     */
    public void createNewSystem() {
        passengerTransportation = new PassengerTransportation();
        stations = new ArrayList<>();
        schedules = new ArrayList<>();
    }

    /**
     * Builds schedules at first, then builds stations and connects them with the created schedules.
     * Created stations and schedules are saved in the builder. We use them in buildBuses() method
     */
    public abstract void buildStationsWithSchedules();

    /**
     * Builds buses and then adds them in transportation system.
     * Stations are sent to the system with created buses.
     */
    public abstract void buildBuses();
}
