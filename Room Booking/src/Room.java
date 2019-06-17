import java.time.LocalDateTime;
import java.util.*;

public abstract class Room {
    private String roomNum;
    private int breakoutSeats;
    private List<Booking> bookings = new ArrayList<Booking>();


    protected Room(String roomNum, int breakoutSeats){
        this.roomNum = roomNum;
        this.breakoutSeats = breakoutSeats;
    }

    public int getBreakoutCount(){
        return breakoutSeats;
    }

    public boolean isBookable(LocalDateTime time, int duration){
        return true;
    }

    public void bookRoom(Client client, LocalDateTime time, int duration){

    }
    public boolean cancelBooking(int reference){
        return true;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

}
