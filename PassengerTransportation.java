package com.company;

import java.util.*;

/**
 * Stores transportation system. This system contains current date and time and sets of buses,
 * stations and tickets. Each bus has a set of stations therefore a set of stations fills when another
 * bus is added to the system. A set of tickets includes an information about all passengers.
 */
public class PassengerTransportation {
    /** Current date in the system */
    private DateAndTime currentDate;

    /** All buses in the system */
    private HashSet<Bus> buses;

    /**
     * All stations in the system
     * Each station includes a schedule
     */
    private HashSet<Station> stations;

    /**
     * All tickets in the system
     * Each ticket includes a passenger
     */
    private HashSet<Ticket> tickets;

    /** Constructor */
    public PassengerTransportation() {

        // Collections to store buses, stations, tickets
        this.buses = new HashSet<>();
        this.stations = new HashSet<>();
        this.tickets = new HashSet<>();
        // default date and time
        currentDate = new DateAndTime(20, 01, 2017,
                5, 03, 30);
    }

    /** Get current time */
    public long getCurrentTime() {
        return currentDate.getMinutesFrom1970();
    }
    /** Get current date */
    public DateAndTime getCurrentDate() {
        return currentDate;
    }

    /** Get buses */
    public HashSet<Bus> getBuses() {
        return buses;
    }
    /** Get stations */
    public HashSet<Station> getStations() {
        return stations;
    }
    /** Get tickets */
    public HashSet<Ticket> getTickets() {
        return tickets;
    }

    /** Show all bus numbers */
    public void showBuses() {
        for (Bus bus: buses)
            System.out.println(bus.getBusNumber());
    }

    /** Show all stations */
    public void showStations() {
        for (Station station: stations)
            System.out.println(station.getName());
    }

    /** Add one or more buses */
    public void addBus(Bus... buses) {
        for (Bus anotherBus: buses) {
            this.buses.add(anotherBus);

            // If the bus passes new stations then add them to the set of stations
            for (Station station: anotherBus.getStations()) {
                // O(1)
                if (!stations.contains(station))
                    stations.add(station);
            }
        }
    }

    /** Add one or more stations */
    public void addStation(Station... stations) {
        for (Station anotherStation: stations)
            this.stations.add(anotherStation);
    }

    /** add new ticket */
    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }
}
