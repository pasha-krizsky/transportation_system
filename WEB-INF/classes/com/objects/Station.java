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
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.Table;

/**
 * This class defines station and it's connected to "stations" table in database
 */
@Entity
@Table(name = "stations")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private int stationId;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "bus_station",
            joinColumns = @JoinColumn(name = "station_id"),
            inverseJoinColumns = @JoinColumn(name = "bus_id"))
    private Set<Bus> buses;

    public Station() {}

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Bus> getBuses() {
        return buses;
    }

    public void setBuses(Set<Bus> buses) {
        this.buses = buses;
    }
}
