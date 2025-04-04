import java.util.List;
import java.util.Scanner;

public class Admin {
    Scanner sc = new Scanner(System.in);
    private static List<Space> spaces;

    Admin(List<Space> spaces) {
        Admin.spaces = spaces;
    }


    public static List<Space> add(int id, String type, double price, boolean availability) {
        Admin.spaces.add(new Space(id, type, price, availability));
        return spaces;
    }

    public static List<Space> remove(int id) {
        spaces.removeIf(s -> s.getId() == id);
        return spaces;
    }

    public static String display() {
        StringBuilder str = new StringBuilder();
        for (Space s : spaces) {
            str.append(s.toString()).append("\n");
        }
        return "The list of all coworking spaces:\n" + str;
    }

    public static List<Space> update(int id, String type, double price, boolean availability) {
        spaces.removeIf(s -> s.getId() == id);
        spaces.add(new Space(id, type, price, availability));
        return spaces;
    }

    public void addSpace() {
        System.out.println("Enter the id of the coworking space:");
        int id = sc.nextInt();
        System.out.println("Enter the type of the coworking space:");
        String type = sc.next();
        System.out.println("Enter the price of the coworking space:");
        double price = sc.nextDouble();
        System.out.println("Enter the availability of the coworking space:");
        boolean availability = sc.nextBoolean();
        spaces = Admin.add(id, type, price, availability);
        System.out.println("The coworking space added successfully!");
    }

    public void removeSpace() {
        if (spaces.isEmpty()) {
            System.out.println("The list of coworking spaces is empty!");
        } else {
            System.out.println("Enter the id of the space:");
            int id = sc.nextInt();
            boolean removingSpace = false;
            for (Space space : spaces) {
                if (space.getId() == id) {
                    spaces = Admin.remove(id);
                    System.out.println("Coworking space was removed!");
                    removingSpace = true;
                    break;
                }
            }
            if (!removingSpace)
                System.out.println("Space with this ID does not exist!");
        }
    }

    public void updateSpace() {
        if (spaces.isEmpty()) {
            System.out.println("The list of coworking spaces is empty!");
        } else {
            System.out.println("Enter the id of the space you would like to update:");
            int id = sc.nextInt();
            boolean updatingSpace = false;
            for (Space space : spaces) {
                if (space.getId() == id) {
                    System.out.println("Enter a new type for the space:");
                    String newType = sc.next();
                    System.out.println("Enter a new price for the space:");
                    double newPrice = sc.nextDouble();
                    System.out.println("Enter a new availability for the space:");
                    boolean newAvailability = sc.nextBoolean();
                    spaces = Admin.update(id, newType, newPrice, newAvailability);
                    System.out.println("The space updated successfully!");
                    updatingSpace = true;
                    break;
                }
            }
            if (!updatingSpace) {
                System.out.println("Space with this ID does not exist!");
            }
        }
    }

    public void displaySpaces() {
        if (spaces.isEmpty()) {
            System.out.println("The list of coworking spaces is empty!");
        } else {
            System.out.println(Admin.display());
        }
    }

}
