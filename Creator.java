package com.company;

/**
 * This class is a Director class that uses concrete builder to create the system
 */
public class Creator {
    /** Link to the builder */
    private PassengerTransportationBuilder builder;

    /**
     * Set new concrete builder
     * @param builder - new builder
     */
    public void setBuilder(PassengerTransportationBuilder builder) {
        this.builder = builder;
    }

    /**
     * This method creates new transportation system. Use it when you want to get constructed system
     * @return - system constructed by concrete builder
     */
    public PassengerTransportation getSystem() {
        return builder.getSystem();
    }

    /**
     * Create a new system by using builder's methods
     */
    public void createSystem() {
        builder.createNewSystem();
        builder.buildStationsWithSchedules();
        builder.buildBuses();
    }
}
