import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Customer {
    Scanner sc = new Scanner(System.in);
    private List<Space> spaces;
    private List<Reservation> reservations;
    private Map<String, String> customers;


    Customer(List<Space> spaceList, List<Reservation> reservationList, Map<String, String> customers) {
        this.spaces = spaceList;
        this.reservations = reservationList;
        this.customers = customers;
    }

    private String browse() {
        String freeSpaces = this.spaces.stream()
                .filter(Space::availability)
                .map(Space::toString)
                .collect(Collectors.joining("\n"));
        return freeSpaces;
    }

    public void browseReservations() {
        if (this.browse().isEmpty()) {
            System.out.println("Unfortunately there are no free spaces");
        } else {
            System.out.println("The list of available coworking spaces:\n" + this.browse());
        }
    }

    private List<Reservation> reserve(String name, String startDate, String endDate, int id) {
        for (Space space : this.spaces) {
            if (space.getId() == id) {
                reservations.add(new Reservation(name, startDate, endDate, id));
            }
        }
        return reservations;
    }

    public void makeReservation() {
        if (this.browse().isEmpty()) {
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
                    reservations = this.reserve(name, startDate, endDate, id);
                    spaces = this.availabilitySpaceChanging(id);
                    System.out.println("Reservation made!");
                    reserved = true;
                    break;
                }
            }
            if (!reserved) {
                System.out.println("Coworking space with this ID does not exist or the space is occupied!");
            }
        }
    }

    private String view() {
        StringBuilder str = new StringBuilder();
        for (Reservation reservation : this.reservations) {
            str.append(reservation.toString()).append("\n");
        }
        return str.toString();
    }

    public void viewReservation() {
        Optional.ofNullable(reservations)
                .filter(reservations -> !reservations.isEmpty())
                .ifPresentOrElse(
                        _ -> System.out.println("The list of your reservations:\n" + this.view()),
                        () -> System.out.println("The list of reservations is empty!")
                );
        if (reservations.isEmpty()) {
            System.out.println("The list of reservations is empty!");
        } else {
            System.out.println("The list of your reservations:\n" + this.view());
        }
    }

    private void cancel(int id) {
        this.reservations.removeIf(reservation -> reservation.getId() == id);
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
                    this.cancel(id);
                    spaces = this.availabilitySpaceChanging(id);
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

    private List<Space> availabilitySpaceChanging(int id) {
        for (Space space : this.spaces) {
            if (space.getId() == id) {
                space.changeAvailability();
            }
        }
        return spaces;
    }

    public String chooseCustomer() {
        System.out.println("Customer List:");
        for (String key : customers.keySet()) {
            System.out.println(key);
        }
        System.out.println("Enter your customer name");
        String customerName;
        while (true) {
            customerName = sc.nextLine();
            if (customers.containsKey(customerName)) {
                break;
            } else {
                System.out.println("Customer not found, please try again");
                sc.nextLine();
            }
        }
        return customerName;
    }
}
