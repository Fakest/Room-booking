import java.io.Serializable;

public class Lab extends Room implements Serializable {

    private int workStations;
    private boolean printer;
    private boolean smartBoard;

	public Lab() {
	}

	public Lab(String roomNum, int workStations, int breakoutSeats, boolean smartBoard, boolean printer) {
        super(roomNum, breakoutSeats);
        this.workStations = workStations;
        this.printer = printer;
        this.smartBoard = smartBoard;
    }

    public int getWorkstationsCount(){
        return workStations;
    }

    public boolean hasPrinter(){
        return printer;
    }

    public boolean hasSmartBoard(){
        return smartBoard;
    }
}
