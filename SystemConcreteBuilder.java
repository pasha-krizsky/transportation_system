package com.company;

import java.util.HashSet;

/**
 * Just an example of how we can declare a concrete builder.
 * Implements two abstract methods: buildStationsWithSchedules() and buildBuses().
 * In this example 3 buses, 5 stations and 5 schedules are created
 */
public class SystemConcreteBuilder extends PassengerTransportationBuilder {

    public void buildStationsWithSchedules() {
        // Schedules
        Schedule schedule1 = new Schedule();
        Schedule schedule2 = new Schedule();
        Schedule schedule3 = new Schedule();
        Schedule schedule4 = new Schedule();
        Schedule schedule5 = new Schedule();

        schedules.add(schedule1);
        schedules.add(schedule2);
        schedules.add(schedule3);
        schedules.add(schedule4);
        schedules.add(schedule5);

        // Stations
        Station station1 = new Station("station1", schedules.get(0));
        Station station2 = new Station("station2", schedules.get(1));
        Station station3 = new Station("station3", schedules.get(2));
        Station station4 = new Station("station4", schedules.get(3));
        Station station5 = new Station("station5", schedules.get(4));

        // Add all stations to sets of stations
        // Set 1
        stations.add(new HashSet<>());
        stations.get(0).add(station1);
        stations.get(0).add(station2);
        stations.get(0).add(station3);
        stations.get(0).add(station4);
        stations.get(0).add(station5);

        // Set 2
        stations.add(new HashSet<>());
        stations.get(1).add(station1);
        stations.get(1).add(station2);
        stations.get(1).add(station3);

        // Set 3
        stations.add(new HashSet<>());
        stations.get(2).add(station3);
        stations.get(2).add(station4);
        stations.get(2).add(station5);
    }

    public void buildBuses() {
        Bus bus1 = new Bus(1, stations.get(0), 6);
        Bus bus2 = new Bus(2, stations.get(1), 10);
        Bus bus3 = new Bus(3, stations.get(2), 11);

        // For the first bus
        schedules.get(0).setBusAndArrivalTime(bus1, new DateAndTime(21, 01, 2017,
                6, 00, 00));
        schedules.get(1).setBusAndArrivalTime(bus1, new DateAndTime(21, 01, 2017,
                7, 00, 00));
        schedules.get(2).setBusAndArrivalTime(bus1, new DateAndTime(21, 01, 2017,
                8, 00, 00));
        schedules.get(3).setBusAndArrivalTime(bus1, new DateAndTime(21, 01, 2017,
                9, 30, 30));
        schedules.get(4).setBusAndArrivalTime(bus1, new DateAndTime(21, 01, 2017,
                11, 10, 00));

        // For the second bus
        schedules.get(0).setBusAndArrivalTime(bus2, new DateAndTime(21, 01, 2017,
                7, 00, 00));
        schedules.get(1).setBusAndArrivalTime(bus2, new DateAndTime(21, 01, 2017,
                9, 20, 00));
        schedules.get(2).setBusAndArrivalTime(bus2, new DateAndTime(21, 01, 2017,
                10, 11, 11));

        // For the third bus
        schedules.get(2).setBusAndArrivalTime(bus3, new DateAndTime(21, 01, 2017,
                6, 31, 30));
        schedules.get(3).setBusAndArrivalTime(bus3, new DateAndTime(21, 01, 2017,
                7, 20, 20));
        schedules.get(4).setBusAndArrivalTime(bus3, new DateAndTime(21, 01, 2017,
                12, 20, 10));

        // Add all buses in our system
        // Stations with schedules are also added with the buses
        passengerTransportation.addBus(bus1, bus2, bus3);
    }
}
