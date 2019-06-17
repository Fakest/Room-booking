import java.time.LocalDateTime;

public class Booking implements Comparable{
    private Client booker;
    private LocalDateTime time;
    private int duration;
    private int reference;

    private Booking(Client booker, LocalDateTime time, int duration, int reference) {
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



    public boolean overlaps(Booking booking){
        return true;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
