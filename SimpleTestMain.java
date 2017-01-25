package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Simple test without Client-Server interaction
 * Check methods of client and companyMember
 */
public class SimpleTestMain {

    /** This test run in main(String[] args) method */
    public static void main(String[] args) {
        // -------------------------------------------------------------------------------------------------------------
        // Preparation

        // The transportation system
        PassengerTransportation system;
        // Create passenger
        Passenger passenger1 = new Passenger("passenger1", "spassenger1",
                15, 8, 1996);
        // Create new system
        Creator creator = new Creator();
        PassengerTransportationBuilder builder = new SystemConcreteBuilder();
        creator.setBuilder(builder);
        creator.createSystem();
        system = creator.getSystem();
        // New client
        Client client = new Client(system, passenger1);
        // New company member
        CompanyMember companyMember = new CompanyMember(system);
        // -------------------------------------------------------------------------------------------------------------
        // Test the client

        // 1. Get schedule for the station (success)
        client.showSchedule(client.getStationByName("station2"));

        // 1. Get schedule for the station (failed)
        client.showSchedule(client.getStationByName("STation2"));

        // 2. Buy ticket (success)
        client.buyTicket(client.getBusByNumber(1), client.getStationByName("station1"));

        // 2. Buy ticket (failed)
        client.buyTicket(client.getBusByNumber(1), client.getStationByName("station1"));

        // 3. Find bus between stations (success)
        client.findBusBetweenStations(client.getStationByName("station1"),
                client.getStationByName("station2"),
                new DateAndTime(5, 0, 0),
                new DateAndTime(13, 0,0));

        // 3. Find bus between stations (failed)
        client.findBusBetweenStations(client.getStationByName("Station1"),
                client.getStationByName("station2"),
                new DateAndTime(5, 0, 0),
                new DateAndTime(13, 0,0));
        // -------------------------------------------------------------------------------------------------------------
        // Test the company member

        // 1. Show all buses
        companyMember.showAllBuses();
        // 2. Show all passengers registered to the bus (success)
        companyMember.showAllPassengersForBus(1);
        // 2. Show all passengers registered to the bus (failed)
        companyMember.showAllPassengersForBus(companyMember.getBusByNumber(0));

        // 3. Add new bus
        List<Station> stations = new ArrayList<>();
        stations.add(companyMember.getStationByName("station1"));
        stations.add(companyMember.getStationByName("station2"));
        stations.add(companyMember.getStationByName("station4"));
        Bus bus5 = new Bus(5, new HashSet<>(stations), 10);
        stations.get(0).getSchedule().setBusAndArrivalTime(bus5, new DateAndTime(3, 30, 0));
        stations.get(1).getSchedule().setBusAndArrivalTime(bus5, new DateAndTime(4, 40, 0));
        stations.get(2).getSchedule().setBusAndArrivalTime(bus5, new DateAndTime(5, 50, 0));
        companyMember.addBus(bus5);

        // 4. Add new stations
        Station newStation1 = new Station("newStation1", new Schedule());
        Station newStation2 = new Station("newStation2", new Schedule());
        companyMember.addStation(newStation1, newStation2);
    }
}
























