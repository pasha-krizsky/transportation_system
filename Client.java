package com.company;

import java.util.HashSet;

/**
 * This class stores all states and behaviors for the clients.
 * The client can get a schedule for the station, search for the bus between two stations at given time-lapse and
 * buy ticket if the client is an passenger
 */
public class Client {
    /** Connect the client with transportation system */
    private PassengerTransportation transportationSystem;

    /** Client might be a passenger */
    private Passenger passenger;

    /**
     * Constructor 1
     * @param transportationSystem - link to transportation system
     */
    public Client(PassengerTransportation transportationSystem) {
        this.transportationSystem = transportationSystem;
        passenger = null;
    }

    /**
     * Constructor 2
     * @param transportationSystem - link to transportation system
     * @param passenger - the client is the passenger
     */
    public Client(PassengerTransportation transportationSystem, Passenger passenger) {
        this.transportationSystem = transportationSystem;
        this.passenger = passenger;
    }

    /**
     * Set the client as the passenger
     * @param passenger - link to the passenger object
     */
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    /**
     * Get bus schedule for the station
     * @param station - station with schedule
     * @return - schedule object for the station
     */
    public Schedule getSchedule(Station station) {
        return station.getSchedule();
    }

    /**
     * Get all stations in the system
     * @return - set of stations in the system
     */
    public HashSet<Station> getStations() {
        return transportationSystem.getStations();
    }

    /**
     * Get station with the name
     * @param name - the name of the station
     * @return - link to the station object or null, if the station with the name doesn't exist
     */
    public Station getStationByName(String name) {
        for (Station station: transportationSystem.getStations()) {
            if (station.getName() == name)
                return station;
        }
        return null;
    }

    /**
     * Get bus with the number
     * @param number - a bus number
     * @return - link to the bus object or null, if the bus with the number doesn't exist
     */
    public Bus getBusByNumber(int number) {
        for (Bus bus: transportationSystem.getBuses()) {
            if (bus.getBusNumber() == number)
                return bus;
        }
        return null;
    }

    /**
     * Show bus schedule at the station
     * @param station - link to the station object
     */
    public void showSchedule(Station station) {
        if (station != null)
            System.out.println(station.getSchedule().toString());
        else
            System.out.println("No stations with this name");
    }

    /**
     * Buy a ticket for a passenger
     * @param bus - desired bus
     * @param station - desired station
     * @return - new ticket or null
     */
    public Ticket buyTicket(Bus bus, Station station) {

        // The client must be a passenger
        if (passenger == null) {
            System.out.println("The client is not a passenger");
            return null;
        }

        // Arrival time for the bus
        DateAndTime arrivalTime = station.getSchedule().getArrivalTime(bus.getBusNumber());

        boolean isTime = false;
        if (arrivalTime.getMinutesFrom1970() - transportationSystem.getCurrentTime() >= 10)
            isTime = true;

        if (!isTime) {
            System.out.println("The are no tickets for the bus");
            return null;
        }


        Ticket ticket = null;
        if (bus.getFreeSeats() > 0 && !bus.getPassengers().contains(passenger))
            ticket = new Ticket(bus.getBusNumber(), passenger);

        if (ticket == null) {
            System.out.println("Sorry, but you cannot buy the ticket :(");
            return null;
        }

        System.out.println("Success! One ticket was bought");
        transportationSystem.addTicket(ticket);
        bus.addPassenger(passenger);
        return ticket;
    }

    /**
     * Find bus between A and B stations at given time-lapse
     * @param A - first station
     * @param B - second station
     * @param date1 - first date
     * @param date2 - second date
     * @return - new bus or null
     */
    public Bus findBusBetweenStations(Station A, Station B, DateAndTime date1, DateAndTime date2) {
        Bus bus = null;

        // Check all buses
        for (Bus anotherBus: transportationSystem.getBuses()) {
            // O(1)
            if (anotherBus.getStations().contains(A) && anotherBus.getStations().contains(B)) {
                // Remember arrival times for the bus
                DateAndTime arrivalTimeForA = A.getSchedule().getArrivalTime(anotherBus.getBusNumber());
                DateAndTime arrivalTimeForB = B.getSchedule().getArrivalTime(anotherBus.getBusNumber());

                if (arrivalTimeForB.getMinutesFrom1970() <= date2.getMinutesFrom1970()
                            && arrivalTimeForB.getMinutesFrom1970() > date1.getMinutesFrom1970()) {
                    if (arrivalTimeForA.getMinutesFrom1970() < date2.getMinutesFrom1970()
                            && arrivalTimeForA.getMinutesFrom1970() >= date1.getMinutesFrom1970()) {
                        if (arrivalTimeForB.getMinutesFrom1970() - arrivalTimeForA.getMinutesFrom1970() > 0) {
                            bus = anotherBus;
                            System.out.println("Bus was found. bus number: " + bus.getBusNumber());
                            break;
                        }
                    }
                }
            }
        }

        if (bus == null)
            System.out.println("Bus was not found");
        return bus;
    }
}
