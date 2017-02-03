package com.interaction;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.objects.*;
import org.hibernate.*;
import utils.HibernateUtil;

import static sun.security.krb5.Confounder.intValue;

/**
 * This class defines some methods for interaction with the database.
 */
public class SystemDAO {

    /**
     * This method returns all passengers from database ordered by date of birth.
     * @return - List of passengers.
     */
    public List<Passenger> getAllPassengers() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Passenger> result = session.createQuery("from com.objects.Passenger order by dateOfBirth").list();

        // Initialize tickets
        for (Passenger p : result)
            Hibernate.initialize(p.getTickets());

        // Commit all changes
        session.getTransaction().commit();

        return result;
    }

    /**
     * This method returns schedule from database ordered by arrival time.
     * @return - All records in the schedule.
     */
    public List<Schedule> getAllSchedules() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Schedule> result = session.createQuery("from com.objects.Schedule order by time").list();

        // Initialize schedule
        for (Schedule s : result)
            Hibernate.initialize(s.getBus());

        // Initialize stations
        for (Schedule s : result)
            Hibernate.initialize(s.getStation());

        // Commit changes
        session.getTransaction().commit();

        return result;
    }

    /**
     * This method returns buses from database ordered by bus numbers.
     * @return - List of buses.
     */
    public List<Bus> getAllBuses() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Bus> result = session.createQuery("from com.objects.Bus order by busNumber").list();

        // Initialize buses
        for (Bus b : result)
            Hibernate.initialize(b.getStations());

        // Commit changes
        session.getTransaction().commit();

        return result;
    }

    /**
     * This method returns stations from database ordered by ID.
     * @return - List of stations.
     */
    public List<Station> getAllStations() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Station> result = session.createQuery("from com.objects.Station order by stationId").list();

        // Initialize buses
        for (Station s : result)
            Hibernate.initialize(s.getBuses());

        // Commit changes
        session.getTransaction().commit();

        return result;
    }

    /**
     * This method returns tickets from database ordered by time.
     * @return - List of tickets
     */
    public List<Ticket> getAllTickets() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Ticket> result = session.createQuery("from com.objects.Ticket order by time").list();

        // Initialize buses, stations, passengers
        for (Ticket t : result) {
            Hibernate.initialize(t.getBus());
            Hibernate.initialize(t.getStation());
            Hibernate.initialize(t.getPassenger());
        }

        // Commit changes
        session.getTransaction().commit();

        return result;
    }

    /**
     * This method adds new bus to database.
     * @param bus - bus object.
     */
    public void addBus(Bus bus) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(bus);
        session.getTransaction().commit();
    }

    /**
     * This method adds new station to database.
     * @param station - station object.
     */
    public void addStation(Station station) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(station);
        session.getTransaction().commit();
    }

    /**
     * This methods returns schedule for concrete station.
     * @param stationId - ID of station.
     * @return - Schedule for the station.
     */
    public List<Schedule> getScheduleByStationId(int stationId) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // HQL query object, select all schedules for the station
        Query query = session.createQuery("from com.objects.Schedule where station.stationId = :st ");
        query.setParameter("st", stationId);
        List<Schedule> result = query.list();

        // Initialize bus
        for (Schedule s : result)
            Hibernate.initialize(s.getBus());

        // Initialize station
        for (Schedule s : result)
            Hibernate.initialize(s.getStation());

        // Commit all changes
        session.getTransaction().commit();

        return result;
    }

    /**
     * This method returns a list of passengers for concrete bus.
     * @param busId - ID of bus.
     * @return - List of passengers.
     */
    public List<Passenger> getPassengersByBusId(int busId) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // HQL query object, select all passenger for the bus
        Query query = session.createQuery(
                "from com.objects.Passenger where passengerId in" +
                "  (select passenger.passengerId from com.objects.Ticket where bus.busId = :bi)");

        query.setParameter("bi", busId);
        List<Passenger> result = query.list();

        // Initialize tickets
        for (Passenger p : result)
            Hibernate.initialize(p.getTickets());

        // Commit all changes
        session.getTransaction().commit();

        return result;
    }

    /**
     * This method returns a list of buses between two stations at given time-lapse.
     * @param stationId1 - ID of first station.
     * @param stationId2 - ID of second station.
     * @param date1 - First time.
     * @param date2 - Last time.
     * @return - List of buses.
     */
    public LinkedList<com.objects.Bus> getBusesBetweenStations(int stationId1, int stationId2,
                                                  Date date1, Date date2) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // SQL query
        SQLQuery query = session.createSQLQuery(
                "SELECT * FROM buses WHERE bus_id IN" +
                        "  (SELECT bus_id FROM schedule WHERE" +
                        "    station_id = :id1 AND arrival_time > :time1 AND bus_id IN" +
                        "      (SELECT bus_id FROM schedule WHERE" +
                        "        station_id= :id2 AND arrival_time < :time2))");

        // Set patameters
        query.setParameter("id1", stationId1);
        query.setParameter("time1", date1);
        query.setParameter("id2", stationId2);
        query.setParameter("time2", date2);

        LinkedList<Bus> res = new LinkedList<>();
        List<Object> result = (List<Object>) query.list();

        Iterator itr = result.iterator();
        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next();

            // One array of Object for each row
            // Read data from each row
            Integer bus_id = Integer.parseInt(String.valueOf(obj[0]));
            Integer bus_number = Integer.parseInt(String.valueOf(obj[1]));
            Integer all_seats = Integer.parseInt(String.valueOf(obj[2]));
            Integer free_seats = Integer.parseInt(String.valueOf(obj[3]));

            // Create bus
            Bus bus = new Bus();
            bus.setBusId(bus_id);
            bus.setAllSeats(all_seats);
            bus.setBusNumber(bus_number);
            bus.setFreeSeats(free_seats);
            // Add bus
            res.add(bus);
        }

        // Commit changes
        session.getTransaction().commit();
        return res;
    }

    /**
     * This method allows to add new ticket in database.
     * @param busId - ID of bus for the ticket.
     * @param stationId - ID of station for the ticket.
     * @param dateFrom - Initial date and time.
     * @param name - Name of a passenger.
     * @param surname - Surname of a passenger.
     * @param dateOfBirth - Date of birth of a passenger.
     * @return - New ticket object or null.
     */
    public Ticket buyTicket(int busId, int stationId, java.util.Date dateFrom,
                            String name, String surname, java.util.Date dateOfBirth) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Ticket ticket = null;

        // Check seats
        Query query = session.createQuery("select count(*) from com.objects.Bus " +
                "where busId=:id and freeSeats > 0");

        query.setParameter("id", busId);
        int count = ((Long) query.uniqueResult()).intValue();
        // No seats
        if (count <= 0) return  null;

        // Check time
        query = session.createQuery("select time from com.objects.Schedule " +
                "where bus.busId=:id1 and station.stationId=:id2");
        query.setParameter("id1", busId);
        query.setParameter("id2", stationId);
        List<Date> dateList = query.list();

        // No bus with station
        if (dateList.size() == 0) {
            return null;
        }
        // Too late
        else if (dateList.get(0).getTime() - dateFrom.getTime() <= 600000) {
            return null;
        }

        // Check passenger
        query = session.createQuery("from com.objects.Passenger where name=:name " +
                "and surName=:surname and dateOfBirth=:dt");
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        query.setParameter("dt", dateOfBirth);
        List<Passenger> pas = query.list();

        Passenger passenger = null;

        // Already exists
        if (pas.size() > 0) {
            passenger = pas.get(0);

            // Check tickets
            query = session.createQuery("select count(*) from com.objects.Ticket  " +
                    "where passenger.name=:name and passenger.surName=:surname " +
                    "and passenger.dateOfBirth=:dt and bus.busId=:bi");

            query.setParameter("name", name);
            query.setParameter("surname", surname);
            query.setParameter("dt", dateOfBirth);
            query.setParameter("bi", busId);

            count = ((Long) query.uniqueResult()).intValue();

            // Ticket already exists
            if (count > 0) {
                return null;
            }
        }
        // New passenger - add to db
        else {
            passenger = new Passenger();
            passenger.setName(name);
            passenger.setDateOfBirth(dateOfBirth);
            passenger.setSurName(surname);
            session.save(passenger);
        }

        // Bus and stations
        Bus bs = (Bus) session.load(Bus.class, busId);
        com.objects.Station st = (com.objects.Station) session.load(com.objects.Station.class, stationId);

        bs.setFreeSeats(bs.getFreeSeats()-1);

        ticket = new com.objects.Ticket();
        ticket.setBus(bs);
        ticket.setStation(st);
        ticket.setTime(dateList.get(0));
        ticket.setTicketId(1);
        ticket.setPassenger(passenger);
        // Initialize fields of new ticket
        Hibernate.initialize(ticket.getStation());
        Hibernate.initialize(ticket.getBus());
        // Initialize list of tickets
        Hibernate.initialize(passenger.getTickets());
        Hibernate.initialize(ticket.getPassenger());

        session.save(ticket);
        session.getTransaction().commit();
        return ticket;
    }
}