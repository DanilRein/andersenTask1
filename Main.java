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
        int option;
        do {
            System.out.println("Choose one of the following options and type it in:");
            System.out.println("1-Admin Login");
            System.out.println("2-Customer Login");
            System.out.println("3-Exit");
            while (true) {
                try {
                    option = sc.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("Please enter a valid option!");
                    sc.nextLine();
                }
            }
            switch (option) {
                case 1:
                    System.out.println("Welcome to Admin menu");
                    Admin admin = new Admin(spaces);
                    int adminOption;
                    do {
                        System.out.println("Choose an option:");
                        System.out.println("1-Add space");
                        System.out.println("2-Remove space");
                        System.out.println("3-Display all spaces");
                        System.out.println("4-Update Space");
                        System.out.println("5-Exit");
                        while (true) {
                            try {
                                adminOption = sc.nextInt();
                                break;
                            } catch (Exception e) {
                                System.out.println("Please enter a valid option!");
                                sc.nextLine();
                            }
                        }
                        switch (adminOption) {
                            case 1:
                                admin.addSpace();
                                break;
                            case 2:
                                admin.removeSpace();
                                break;
                            case 3:
                                admin.displaySpaces();
                                break;
                            case 4:
                                admin.updateSpace();
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
                    Customer customer = new Customer(spaces, reservations);
                    int customerOption;
                    do {
                        System.out.println("Choose an option:");
                        System.out.println("1-Browse available coworking spaces");
                        System.out.println("2-Make a reservation");
                        System.out.println("3-View my reservations");
                        System.out.println("4-Cancel reservation");
                        System.out.println("5-Exit");
                        while (true) {
                            try {
                                customerOption = sc.nextInt();
                                break;
                            } catch (Exception e) {
                                System.out.println("Please enter a valid option!");
                                sc.nextLine();
                            }
                        }
                        switch (customerOption) {
                            case 1:
                                customer.browseReservations();
                                break;
                            case 2:
                                customer.makeReservation();
                                break;
                            case 3:
                                customer.viewReservation();
                                break;
                            case 4:
                                customer.cancelReservation();
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
