public class Space {
    private int id;
    private String type;
    private Double price;
    private boolean availability;

    public Space(int id, String type, Double price, Boolean availability) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public boolean availability() {
        return availability;
    }

    public boolean changeAvailability() {
        availability = !availability;
        return availability;
    }

    public String toString() {
        return "ID: " + id + "; Type: " + type + "; Price: " + price + "; Availability: " + availability;
    }
}
