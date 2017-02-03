package com.forms;

import com.objects.Schedule;

import java.util.List;

/**
 * This class defines a model for show schedule form. (MVC)
 */
public class ShowScheduleForm {
    /** Schedule to show */
    List<Schedule> records;

    public List<Schedule> getRecords() {
        return records;
    }

    public void setRecords(List<Schedule> records) {
        this.records = records;
    }
}
