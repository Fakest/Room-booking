import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;

public class BookingManager {
    private int bookingfRef;
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
    public void listBookings(Client client){

    }
    private boolean clientExists(String familyName, String givenName){
        for (Client c: clients
             ) {
            if(c.getFamilyName().equals(familyName) && c.getGivenName().equals(givenName)){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public void initialize(){
        rooms.put("006", new Meeting("006", 12));
        rooms.put("008", new Lab("008", 18, 10, true, true));
        rooms.put("011", new Lab("011", 20, 0, true, true));
        rooms.put("013", new Lab("013", 6, 0, false, true));
        rooms.put("014", new Lab("014",18, 2, true, true));
        rooms.put("015", new Lab("015", 18, 10, true, true));
        rooms.put("017", new Lab("018", 18, 10, true, true));
        rooms.put("108", new Conference("108", 20, true));
        rooms.put("120", new Lab("120",18, 0, true, true));
        rooms.put("301", new Lab("301",18, 6, true, true));
    }
}