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

	public String getRoomNum() {
		return roomNum;
	}
    public boolean isBookable(LocalDateTime time, int duration){
		for (Booking booking: bookings
			 ) {
			if (booking.overlaps())
		}
        return true;
    }

    public void bookRoom(Client client, LocalDateTime time, int duration){
		Random r = new Random();
		Booking thisBooking = new Booking(client, time, duration, r.nextInt(9999));
		bookings.add(thisBooking);
		System.out.println(thisBooking);
    }
    public boolean cancelBooking(int reference){
        return true;
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
