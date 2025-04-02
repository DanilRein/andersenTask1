import java.util.ArrayList;
import java.util.List;

public class Customer {
    private List<Space> spaceList = new ArrayList<>();
    private List<Reservation> reservationsList = new ArrayList<>();

    Customer(List<Space> spaceList) {
        this.spaceList = spaceList;
    }

    public String browse() {
        StringBuilder str = new StringBuilder();
        for (Space space : spaceList) {
            if (space.availability())
                str.append(space.toString()).append("\n");
        }
        return str.toString();
    }

    public List<Reservation> makeReservation(String name, String startDate, String endDate, int id) {
        for (Space space : spaceList) {
            if (space.getId() == id) {
                reservationsList.add(new Reservation(name, startDate, endDate, id));
            }
        }
        return reservationsList;
    }

    public void viewReservation(List<Reservation> reservations) {
        if (!reservations.isEmpty()) {
            System.out.println("The list of your reservations:");
            for (Reservation reservation : reservations) {
                System.out.println(reservation.toString());
            }
        } else System.out.println("You have no reservations");
    }

    public void cancelReservation(int id) {
        reservationsList.removeIf(reservation -> reservation.getId() == id);
    }

    public List<Space> availabilitySpaceChanging(int id) {
        for (Space space : spaceList) {
            if (space.getId() == id) {
                space.changeAvailability();
            }
        }
        return spaceList;
    }
}
