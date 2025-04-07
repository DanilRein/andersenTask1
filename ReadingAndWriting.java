import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ReadingAndWriting implements Serializable {
    private List<Space> spaces;
    private List<Reservation> reservations;

    ReadingAndWriting(List<Space> spaces, List<Reservation> reservations) {
        this.spaces = spaces;
        this.reservations = reservations;
    }

    public void readSpaces(String SpacesFileName) {
        try (FileReader fileReader = new FileReader(SpacesFileName);
             BufferedReader br = new BufferedReader(fileReader)) {
            while (br.ready()) {
                String line = br.readLine();
                if (line.isEmpty())
                    continue;
                String[] tokens = line.split(" ");
                spaces.add(new Space(Integer.parseInt(tokens[1]), tokens[3],
                        Double.parseDouble(tokens[5]), Boolean.parseBoolean(tokens[7])));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeSpaces(String SpacesFileName) {
        try (FileWriter fileWriter = new FileWriter(SpacesFileName);
             BufferedWriter br = new BufferedWriter(new BufferedWriter(fileWriter))) {
            for (Space space : spaces) {
                br.write(space.toString());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readReservations(String ReservationsFileName) {
        try (ObjectInputStream readReserve = new ObjectInputStream(new BufferedInputStream(new FileInputStream(ReservationsFileName)))) {
            List<Reservation> load = (ArrayList<Reservation>) readReserve.readObject();
            reservations.addAll(load);
        } catch (EOFException e) {
            System.out.println("NOTE: File with reservations is empty");
            reservations = new ArrayList<>();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeReservations(String ReservationsFileName) {
        try (ObjectOutputStream writeReserve = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(ReservationsFileName)))) {
            writeReserve.writeObject(reservations);
        } catch (IOException e) {
            throw new RuntimeException("File not found: " + e.getMessage());
        }
    }
}
