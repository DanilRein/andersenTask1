import java.io.Serializable;

public class Reservation implements Serializable {
    private final String name;
    private final String startDate;
    private final String endDate;
    private final int id;

    Reservation(String name, String startDate, String endDate, int id) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "Name: " + name + " Start_Date: " + startDate + " End_Date: " + endDate + " Coworking_space_ID " + id + "\n";
    }
}
