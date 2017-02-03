package com.objects;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * This class defines bus and it's connected to "buses" table in database
 */
@Entity
@Table(name="buses")
public class Bus {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="bus_id")
    private int busId;

    @Column(name="bus_number")
    private int busNumber;

    @Column(name="all_seats")
    private int allSeats;

    @Column(name="free_seats")
    private int freeSeats;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "bus_station",
            joinColumns = @JoinColumn(name = "bus_id"),
            inverseJoinColumns = @JoinColumn(name = "station_id"))
    private Set<Station> stations;

    public Bus() {}

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public int getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(int busNumber) {
        this.busNumber = busNumber;
    }

    public int getAllSeats() {
        return allSeats;
    }

    public void setAllSeats(int allSeats) {
        this.allSeats = allSeats;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    public Set<Station> getStations() {
        return stations;
    }

    public void setStations(Set<Station> stations) {
        this.stations = stations;
    }
}
