import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.io.Serializable;

public abstract class Room implements Serializable {
    private String roomNum;
    private int breakoutSeats;
    private List<Booking> bookings = new ArrayList<Booking>();

	public Room() {
	}

	protected Room(String roomNum, int breakoutSeats){
        this.roomNum = roomNum;
        this.breakoutSeats = breakoutSeats;
    }

    public int getBreakoutCount(){
        return breakoutSeats;
    }

	public String getRoomNum() {
		return roomNum;
	}
    public boolean isBookable(LocalDateTime time, int duration){
		boolean valid = true;
		for (Booking booking: bookings
			 ) {
			if (booking.overlaps(time, duration)){
				valid = false;
			}

		}
		return valid;
    }

    public void bookRoom(Client client, LocalDateTime time, int duration){
		Random r = new Random();
		Booking thisBooking = new Booking(client, time, duration, r.nextInt(9999));
		bookings.add(thisBooking);
		System.out.println(thisBooking);
    }
    public boolean cancelBooking(int reference){
		for (Booking booking: bookings
			 ) {
			if(booking.getReference() == reference){
				bookings.remove(booking);
				return true;
			}
		}
		return false;

    }

    public List<Booking> getBookings() {
        return bookings;
    }

	@Override
	public String toString() {
		return "Room{" +
				"roomNum='" + roomNum + '\'' +
				", breakoutSeats=" + breakoutSeats +
				", bookings=" + bookings +
				'}';
	}
}
