import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Space> spaces = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();
        spaces.add(new Space(123, "open", 39.99, true));
        spaces.add(new Space(231, "closed", 69.99, false));
        spaces.add(new Space(321, "open", 29.99, true));
        spaces.add(new Space(412, "open", 34.99, false));
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome!");
        int option = -1;
        System.out.println("!WARNING!\n \"ID\" is an integer,\n \"Type\" is a String," +
                "\n \"Price\" is a float number\n \"Availability\" is a boolean(true - free, false - not free)");
        do {
            System.out.println("Choose one of the following options and type it in:");
            System.out.println("1-Admin Login");
            System.out.println("2-Customer Login");
            System.out.println("3-Exit");
            try {
                option = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter a valid option!");
                sc.nextLine();
            }
            switch (option) {
                case 1:
                    System.out.println("Welcome to Admin menu");
                    Admin admin = new Admin(spaces);
                    int adminOption;
                    do {
                        System.out.println("Choose an option:");
                        System.out.println("1-Add reservation");
                        System.out.println("2-Remove reservation");
                        System.out.println("3-Display All reservations");
                        System.out.println("4-Update Coworking Space");
                        System.out.println("5-Exit");
                        try {
                            adminOption = sc.nextInt();
                        } catch (Exception e) {
                            System.out.println("Please enter a valid option!");
                            adminOption = 5;
                        }
                        switch (adminOption) {
                            case 1:
                                System.out.println("Enter the id of the reservation:");
                                int id = sc.nextInt();
                                System.out.println("Enter the type of the coworking space:");
                                String type = sc.next();
                                System.out.println("Enter the price of the coworking space:");
                                double price = sc.nextDouble();
                                System.out.println("Enter the availability of the coworking space:");
                                boolean availability = sc.nextBoolean();
                                spaces = admin.addSpace(id, type, price, availability);
                                System.out.println("The coworking space added successfully!");
                                break;
                            case 2:
                                System.out.println("Enter the id of the space:");
                                int spaceIdForRemoving = sc.nextInt();
                                boolean removingSpace = false;
                                for (Space space : spaces) {
                                    if (space.getId() == spaceIdForRemoving) {
                                        spaces = admin.removeSpace(spaceIdForRemoving);
                                        System.out.println("Coworking space was removed!");
                                        removingSpace = true;
                                        break;
                                    }
                                }
                                if (!removingSpace)
                                    System.out.println("Space with this ID does not exist!");
                                break;
                            case 3:
                                System.out.println("The list of all coworking spaces:");
                                System.out.println(admin.display());
                                break;
                            case 4:
                                System.out.println("Enter the id of the space you would like to update:");
                                int spaceForUpdateId = sc.nextInt();
                                System.out.println("Enter a new type for the space:");
                                String newType = sc.next();
                                System.out.println("Enter a new price for the space:");
                                double newPrice = sc.nextDouble();
                                System.out.println("Enter a new availability for the space:");
                                boolean newAvailability = sc.nextBoolean();
                                spaces = admin.updateSpace(spaceForUpdateId, newType, newPrice, newAvailability);
                                System.out.println("The space updated successfully!");
                                break;
                            case 5:
                                System.out.println("You were redirected to the main menu!");
                                break;
                            default:
                                System.out.println("Try one more time");
                                break;
                        }
                    } while (adminOption != 5);
                    break;
                case 2:
                    System.out.println("Welcome to Customer menu");
                    Customer customer = new Customer(spaces);
                    int customerOption;
                    do {
                        System.out.println("Choose an option:");
                        System.out.println("1-Browse available coworking spaces");
                        System.out.println("2-Make a reservation");
                        System.out.println("3-View my reservations");
                        System.out.println("4-Cancel reservation");
                        System.out.println("5-Exit");
                        try {
                            customerOption = sc.nextInt();
                        } catch (Exception e) {
                            System.out.println("Please enter a valid option!");
                            customerOption = 5;
                        }
                        switch (customerOption) {
                            case 1:
                                System.out.println("The list of available coworking spaces:");
                                System.out.println(customer.browse());
                                break;
                            case 2:
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
                                        reservations = customer.makeReservation(name, startDate, endDate, id);
                                        spaces = customer.availabilitySpaceChanging(id);
                                        System.out.println("Reservation made!");
                                        reserved = true;
                                        break;
                                    }
                                }
                                if (!reserved) {
                                    System.out.println("Coworking space with this ID does not exist!");
                                }
                                break;
                            case 3:
                                customer.viewReservation(reservations);
                                break;
                            case 4:
                                System.out.println("Enter the ID of the reservation you want to delete:");
                                int removeId = sc.nextInt();
                                boolean idForRemoving = false;
                                for (Reservation reservation : reservations) {
                                    if (reservation.getId() == removeId) {
                                        customer.cancelReservation(removeId);
                                        spaces = customer.availabilitySpaceChanging(removeId);
                                        System.out.println("Reservation removed successfully!");
                                        idForRemoving = true;
                                        break;
                                    }
                                }
                                if (!idForRemoving) {
                                    System.out.println("Reservation with this ID does not exist!");
                                }
                                break;
                            case 5:
                                System.out.println("You were redirected to the main menu!");
                                break;
                            default:
                                System.out.println("Try one more time");
                                break;
                        }
                    } while (customerOption != 5);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Try one more time");
                    break;
            }
        } while (option != 3);
    }
}
