import java.io.Serializable;
import java.time.LocalDateTime;

public class Booking implements Serializable {
    private Client booker;
    private LocalDateTime time;
    private int duration;
    private int reference;

    public Booking(Client booker, LocalDateTime time, int duration, int reference) {
        this.booker = booker;
        this.time = time;
        this.duration = duration;
        this.reference = reference;
    }

    public Client getBooker() {
        return booker;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }

    public int getReference() {
        return reference;
    }



    public boolean overlaps(LocalDateTime slot, int duration){
    	LocalDateTime booking1start = this.time;
    	LocalDateTime booking1end = this.time.plusHours(this.duration);
    	LocalDateTime booking2start = slot;
    	LocalDateTime booking2end = slot.plusHours(duration);
    	if(booking1start.isBefore(booking2end) && booking1end.isAfter(booking2start)){
    		return true;
		}else{
    		return false;
		}
    }

	@Override
	public String toString() {
		return "Booking{" +
				"booker=" + booker +
				", time=" + time +
				", duration=" + duration +
				", reference=" + reference +
				'}';
	}

}
