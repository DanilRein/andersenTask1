public class Space {
    private final int id;
    private final String type;
    private final Double price;
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

    public void changeAvailability() {
        availability = !availability;
    }

    public String toString() {
        return "ID: " + id + "; Type: " + type + "; Price: " + price + "; Availability: " + availability;
    }
}
