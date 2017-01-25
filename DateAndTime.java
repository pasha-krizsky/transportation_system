package com.company;

/**
 * This class describes date and time. Takes less memory then "Calendar" or "SimpleDateFormat" from java.util.
 * In order to compare two DateAndTime objects getMinutesFrom1970() method can be used
 */
public class DateAndTime {
    // Date
    private int day;
    private int month;
    private int year;

    // Time
    private int seconds;
    private int minutes;
    private int hours;

    /**
     * Constructor 1. Sets default data and time
     */
    public DateAndTime() {
        day     = 21;
        month   = 1;
        year    = 2017;
        seconds = 0;
        minutes = 0;
        hours   = 0;
    }

    /**
     * Constructor 2. Sets default data and requires the time
     * @param hours - amount of hours
     * @param minutes - amount of minutes
     * @param seconds - amount of seconds
     */
    public DateAndTime(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        day   = 21;
        month = 01;
        year  = 2017;
    }

    /**
     * Constructor 3. Requires date and time
     */
    public DateAndTime(int day, int month, int year,
                       int hours, int minutes, int seconds) {
        this.day     = day;
        this.month   = month;
        this.year    = year;
        this.hours   = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    /**
     * Get date as a string object
     * Example: "11.12.2017 15:13:00"
     * @return date as a string object
     */
    public String getDate() {
        return day
                + "." + month
                + "." + year
                + " " + hours
                + ":" + minutes
                + ":" + seconds;
    }

    /** Get day */
    public int getDay() {
        return day;
    }
    /** Get month */
    public int getMonth() {
        return month;
    }
    /** Get year */
    public int getYear() {
        return year;
    }
    /** Get hours */
    public int getHours() {
        return hours;
    }
    /** Get minutes */
    public int getMinutes() {
        return minutes;
    }
    /** Get seconds */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Get amount of minutes since 01.01.1970 00:00
     * @return minutes from the beginning of 1970
     */
    public long getMinutesFrom1970() {

        // Amount of years, month and days
        int amountYears = year - 1970;
        int amountMonth = month - 1;
        int amountDays  = day - 1;

        // Minutes in one year (average)
        long minutesInYear = 525960;
        long minutesInAllYears = amountYears * minutesInYear;

        int daysForMonth = 0;

        switch (amountMonth) {
            case 0:  daysForMonth = 0;   break;
            case 1:  daysForMonth = 31;  break;
            case 2:  daysForMonth = 59;  break;
            case 3:  daysForMonth = 90;  break;
            case 4:  daysForMonth = 120; break;
            case 5:  daysForMonth = 151; break;
            case 6:  daysForMonth = 181; break;
            case 7:  daysForMonth = 212; break;
            case 8:  daysForMonth = 243; break;
            case 9:  daysForMonth = 273; break;
            case 10: daysForMonth = 304; break;
            case 11: daysForMonth = 334; break;
        }

        if (((year - 1972) % 4 == 0) && amountMonth >= 2)
            daysForMonth += 1;

        int daysInYear = daysForMonth + amountDays;
        double minutesSince1970 = minutesInAllYears + daysInYear * 24 * 60 + hours * 60 + minutes;
        return (long)minutesSince1970;
    }

    /**
     * String representation of an object
     * @return date as a string object
     */
    @Override
    public String toString() {
        return day
                + "." + month
                + "." + year
                + " " + hours
                + ":" + minutes
                + ":" + seconds;
    }
}
