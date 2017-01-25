package com.company;

/**
 * Stores a ticket. Each ticket has information about passenger and bus number
 */
public class Ticket {
    /** The number of the bus */
    private int busNumber;
    /** Name, surname and date of birth of the passenger */
    private Passenger passenger;

    /** Constructor */
    public Ticket(int busNumber, Passenger passenger) {
        this.busNumber = busNumber;
        this.passenger = passenger;
    }

    /**
     * Get bus number
     * @return bus number
     */
    public int getBusNumber() {
        return busNumber;
    }

    /**
     * Get Passenger
     * @return passenger
     */
    public Passenger getPassenger() {
        return passenger;
    }

    /** A string representation of an object */
    @Override
    public String toString() {
        return "Ticket: "
                + "bus number: "
                + busNumber
                + ", passenger: "
                + passenger.getName()
                + " "
                + passenger.getSurname();
    }
}
