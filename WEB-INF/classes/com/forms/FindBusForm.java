package com.forms;

import com.objects.Bus;

import java.util.List;
/**
 * This class defines a model for find bus form. (MVC)
 */
public class FindBusForm {
    /** List of buses to show */
    private List<Bus> buses;

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }
}
