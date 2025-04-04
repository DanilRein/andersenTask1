import java.util.List;
import java.util.Scanner;

public class Customer {
    Scanner sc = new Scanner(System.in);
    private static List<Space> spaces;
    private static List<Reservation> reservations;

    Customer(List<Space> spaceList, List<Reservation> reservationList) {
        Customer.spaces = spaceList;
        Customer.reservations = reservationList;
    }

    private static String browse() {
        StringBuilder str = new StringBuilder();
        for (Space space : spaces) {
            if (space.availability())
                str.append(space).append("\n");
        }
        return str.toString();
    }

    public void browseReservations() {
        if (Customer.browse().isEmpty()) {
            System.out.println("Unfortunately there are no free spaces");
        } else {
            System.out.println("The list of available coworking spaces:" + Customer.browse());
        }
    }

    private static List<Reservation> reserve(String name, String startDate, String endDate, int id) {
        for (Space space : spaces) {
            if (space.getId() == id) {
                reservations.add(new Reservation(name, startDate, endDate, id));
            }
        }
        return reservations;
    }

    public void makeReservation() {
        if (Customer.browse().isEmpty()) {
            System.out.println("Unfortunately there are no free spaces");
        } else {
            System.out.println("Enter the name of the reservation:");
            String name = sc.next();
            System.out.println("Enter the start date:");
            String startDate = sc.next();
            System.out.println("Enter the end date:");
            String endDate = sc.next();
            System.out.println("Enter the id of the free coworking space:");
            int id = sc.nextInt();
            boolean reserved = false;
            for (Space space : spaces) {
                if (space.getId() == id && space.availability()) {
                    reservations = Customer.reserve(name, startDate, endDate, id);
                    spaces = Customer.availabilitySpaceChanging(id);
                    System.out.println("Reservation made!");
                    reserved = true;
                    break;
                }
            }
            if (!reserved) {
                System.out.println("Coworking space with this ID does not exist!");
            }
        }
    }

    private static String view() {
        StringBuilder str = new StringBuilder();
        for (Reservation reservation : reservations) {
            str.append(reservation.toString()).append("\n");
        }
        return str.toString();
    }

    public void viewReservation() {
        if (reservations.isEmpty()) {
            System.out.println("The list of reservations is empty!");
        } else {
            System.out.println("The list of your reservations:\n" + Customer.view());
        }
    }

    private static void cancel(int id) {
        reservations.removeIf(reservation -> reservation.getId() == id);
    }

    public void cancelReservation() {
        if (reservations.isEmpty()) {
            System.out.println("The list of reservations is empty!");
        } else {
            System.out.println("Enter the ID of the reservation you want to delete:");
            int id = sc.nextInt();
            boolean idForRemoving = false;
            for (Reservation reservation : reservations) {
                if (reservation.getId() == id) {
                    Customer.cancel(id);
                    spaces = Customer.availabilitySpaceChanging(id);
                    System.out.println("Reservation removed successfully!");
                    idForRemoving = true;
                    break;
                }
            }
            if (!idForRemoving) {
                System.out.println("Reservation with this ID does not exist!");
            }
        }
    }

    private static List<Space> availabilitySpaceChanging(int id) {
        for (Space space : spaces) {
            if (space.getId() == id) {
                space.changeAvailability();
            }
        }
        return spaces;
    }
}
