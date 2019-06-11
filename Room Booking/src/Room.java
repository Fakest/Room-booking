import java.time.LocalDateTime;
import java.util.*;

public abstract class Room {
    private String roomNum;
    private int breakoutSeats;
    private int workstations;

    private List<Booking> bookings = new ArrayList<Booking>();


    protected Room(String roomNum, int breakoutSeats, int workstations){
        this.roomNum = roomNum;
        this.breakoutSeats = breakoutSeats;
        this.workstations = workstations;
    }

    public int getBreakoutCount(){
        return 1;
    }

    public boolean isBookable(LocalDateTime time, int duration){
        return true;
    }

    public void bookRoom(Client client, LocalDateTime time, int duration){

    }
    public boolean cancelBooking(int reference){

    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public int getWorkStations(){
        return workstations;
    }

}