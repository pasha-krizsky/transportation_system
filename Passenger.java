package com.company;

/**
 * This class contains information about passenger
 */
public class Passenger {
    /** A name of the passenger */
    private String name;
    /** A surname of the passenger */
    private String surname;

    /** A date of birth of the passenger */
    private class DateOfBirth {
        public String DAY, MONTH, YEAR;
    }
    private DateOfBirth dateOfBirth;

    /**
     * Creates new passenger object
     * @param name - the name of the passenger
     * @param surname - the surname of the passenger
     * @param day - the day of birth
     * @param month - the month of birth
     * @param year - the year of birth
     */
    public Passenger(String name, String surname, Integer day, Integer month, Integer year) {
        // Write name and surname of a new passenger
        this.name = name;
        this.surname = surname;

        // Set date of birth
        dateOfBirth = new DateOfBirth();
        if (day >= 1 && day < 10)
            dateOfBirth.DAY = "0" + day.toString();
        else
            dateOfBirth.DAY = day.toString();
        if (month >= 1 && month < 10)
            dateOfBirth.MONTH = "0" + month.toString();
        else
            dateOfBirth.MONTH = month.toString();

        dateOfBirth.YEAR = year.toString();
    }

    /** Get day of birth */
    public String getDayOfBirth() {
        return dateOfBirth.DAY;
    }
    /** Get month of birth */
    public String getMonthOfBirth() {
        return dateOfBirth.MONTH;
    }
    /** Get year of birth */
    public String getYearOfBirth() {
        return dateOfBirth.YEAR;
    }

    /** Get name of passenger */
    public String getName() {
        return name;
    }
    /** Get surname of passenger */
    public String getSurname() {
        return surname;
    }

    /** Show name and surname */
    public void showFullName() {
        System.out.println(name + " " + surname);
    }

    /** Show date of birth */
    public void showDateOfBirth() {
        System.out.println(dateOfBirth.DAY + "." + dateOfBirth.MONTH + "." + dateOfBirth.YEAR);
    }

    /** A string representation of an object */
    @Override
    public String toString() {
        return name + " " + surname + " " + dateOfBirth.DAY + "." + dateOfBirth.MONTH + "." + dateOfBirth.YEAR;
    }
}
