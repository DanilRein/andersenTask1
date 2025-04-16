import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, String> customers = new HashMap<>();
        customers.put("Daniil Radevich", "DR");
        customers.put("Andrei Khaustov", "AK");
        customers.put("Maria Ivanova", "MI");
        String fileWithCoworkingSpaces = "CoworkingSpaces.txt";
        List<Space> spaces = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();
        ReadingAndWriting readingAndWriting = new ReadingAndWriting(spaces, reservations);
        readingAndWriting.readSpaces(fileWithCoworkingSpaces);
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
                                readingAndWriting.writeSpaces(fileWithCoworkingSpaces);
                                break;
                            case 2:
                                admin.removeSpace();
                                readingAndWriting.writeSpaces(fileWithCoworkingSpaces);
                                break;
                            case 3:
                                admin.displaySpaces();
                                break;
                            case 4:
                                admin.updateSpace();
                                readingAndWriting.writeSpaces(fileWithCoworkingSpaces);
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
                    Customer customer = new Customer(spaces, reservations, customers);
                    String fileWithReservations = "Reservations" + customers.get(customer.chooseCustomer());
                    readingAndWriting.readReservations(fileWithReservations);
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
                                readingAndWriting.writeReservations(fileWithReservations);
                                readingAndWriting.writeSpaces(fileWithCoworkingSpaces);
                                break;
                            case 3:
                                customer.viewReservation();
                                break;
                            case 4:
                                customer.cancelReservation();
                                readingAndWriting.writeReservations(fileWithReservations);
                                readingAndWriting.writeSpaces(fileWithCoworkingSpaces);
                                break;
                            case 5:
                                reservations.clear();
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
