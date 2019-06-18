public class Conference extends Room{
    private boolean smartBoard;

    public Conference(String roomNum, int breakoutSeats, boolean smartBoard) {
        super(roomNum, breakoutSeats);
        this.smartBoard = smartBoard;
    }

	@Override
	public String toString() {
		return "Conference{" +
				"smartBoard=" + smartBoard +
				'}';
	}

	public boolean hasSmartBoard(){
        return smartBoard;
    }
}
