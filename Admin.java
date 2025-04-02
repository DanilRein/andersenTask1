import java.util.ArrayList;
import java.util.List;

public class Admin {
    List<Space> spaces = new ArrayList<>();

    Admin(List<Space> spaces) {
        this.spaces = spaces;
    }


    public List<Space> addSpace(int id, String type, double price, boolean availability) {
        this.spaces.add(new Space(id, type, price, availability));
        return spaces;
    }

    public List<Space> removeSpace(int id) {
        spaces.removeIf(s -> s.getId() == id);
        return spaces;
    }

    public String display() {
        StringBuilder str = new StringBuilder();
        for (Space s : spaces) {
            str.append(s.toString()).append("\n");
        }
        return str.toString();
    }

    public List<Space> updateSpace(int id, String type, double price, boolean availability) {
        spaces.removeIf(s -> s.getId() == id);
        spaces.add(new Space(id, type, price, availability));
        return spaces;
    }


}
