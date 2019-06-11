import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;

public class BookingManager {
    private int bookinfRef;
    private File dataFile;
    private LinkedList<Client> clients = new LinkedList<>();
    private HashMap<String, Room> rooms = new HashMap<String, Room>();

    public BookingManager(File dataFile) {
        this.dataFile = dataFile;
    }

    public void loadData(){

    }

    public void saveData(){

    }
    public boolean addClient(){
        clients.add();
        return true;
    }
    public String findBooking(LocalDateTime date, int duration, int reference){

    }
    public String findBooking(LocalDateTime date, int duration, int reference, int breakoutSeats){

    }
    public int bookRoom(Client client, String roomNum, LocalDateTime date, int duration){

    }
    public boolean cancelBooking(int reference){

    }
    public void listBookings(String roomNum){

    }
    public void listBookings(Client clients){

    }
    private boolean clientExists(String familyName, String givenName){

    }
}