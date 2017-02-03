import com.objects.*;
import com.interaction.*;
import java.util.List;

public class CompanyMemberMain {

    public static void main(String[] args) {
        SystemDAO dao = new SystemDAO();

        // 1. Add new bus
        Bus bus = new Bus();
        bus.setBusNumber(100);
        bus.setFreeSeats(100);
        bus.setAllSeats(100);
        dao.addBus(bus);

        // 2. Add new station
        Station station = new Station();
        station.setName("newStation");
        dao.addStation(station);

        // 3. Show all buses
        System.out.println("List of Buses");
        System.out.println("----------------");
        List<Bus> bList = dao.getAllBuses();

        for (Bus b : bList) {
            System.out.println("id: " + b.getBusId() + " number: " + b.getBusNumber()
                    + " free seats: " + b.getFreeSeats()
                    + " number of stations: " + b.getStations().size());
        }
        System.out.println("----------------");

        // 4. Show all passengers for the bus
        List<Passenger> passengers = dao.getPassengersByBusId(bList.get(0).getBusId());
        for (Passenger p : passengers) {
            System.out.println("name: " + p.getName() + "; surname: " + p.getSurName()
                    + "; date of birth: " + p.getDateOfBirth());
        }
        System.out.println("----------------");
    }
}

