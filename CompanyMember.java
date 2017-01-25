package com.company;

import java.util.HashSet;

/**
 * This class stores all states and behaviors for the company members.
 * A company member can add new buses and stations to the system, watch all buses in the system and
 * watch all passengers for a bus
 */
public class CompanyMember {
    /** Link to transportation system */
    private PassengerTransportation transportationSystem;

    /** Constructor */
    public CompanyMember(PassengerTransportation transportationSystem) {
        this.transportationSystem = transportationSystem;
    }

    public Bus getBusByNumber(int number) {
        for (Bus bus: transportationSystem.getBuses()) {
            if (bus.getBusNumber() == number)
                return bus;
        }
        return null;
    }

    public Station getStationByName(String name) {
        for (Station station: transportationSystem.getStations()) {
            if (station.getName() == name)
                return station;
        }
        return null;
    }

    /** Add new buses */
    public void addBus(Bus... buses) {
        transportationSystem.addBus(buses);
    }

    /** Add new stations */
    public void addStation(Station... stations) {
        transportationSystem.addStation(stations);
    }

    /** Show passengers registered on the bus */
    public void showAllPassengersForBus(int busNumber) {
        System.out.println("The passengers registered to the bus " + busNumber + ":");
        for (Ticket ticket: transportationSystem.getTickets()) {
            if (ticket.getBusNumber() == busNumber) {
                System.out.println(ticket.getPassenger().toString());
            }
        }
    }

    /** Show passengers registered on the bus */
    public void showAllPassengersForBus(Bus bus) {
        if (bus != null) {
            System.out.println("The passengers registered to the bus " + bus.getBusNumber() + ":");
            for (Ticket ticket : transportationSystem.getTickets()) {
                if (ticket.getBusNumber() == bus.getBusNumber()) {
                    System.out.println(ticket.getPassenger().toString());
                }
            }
        }
        else
            System.out.println("There is no such bus");
    }

    /** Show bus numbers for all buses in the system */
    public void showAllBuses() {
        System.out.print("The buses in the system: ");
        for (Bus bus: transportationSystem.getBuses()) {
            System.out.print(bus.getBusNumber() + " ");
        }
        System.out.println();
    }

    public HashSet<Station> getStations() {
        return transportationSystem.getStations();
    }

}
