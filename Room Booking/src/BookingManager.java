import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class BookingManager {
    private int bookingfRef;
    private File dataFile;
    private LinkedList<Client> clients = new LinkedList<>();
    private HashMap<String, Room> rooms = new HashMap<String, Room>();
	Scanner in = new Scanner(System.in);

    public BookingManager(File dataFile) {
        this.dataFile = dataFile;
    }

    public void loadData(){

    }

    public void saveData(){

    }
    public boolean addClient(String familyName, String givenName, String email, String number){
        clients.add(new Client(familyName, givenName, email, number));
        return true;
    }
    public String findBooking(LocalDateTime date, int duration, int reference){

    }
    public String findBooking(LocalDateTime date, int duration, int reference, int breakoutSeats){

    }
    public void bookRoom(Client client, String roomNum, LocalDateTime date, int duration){
		Room currRoom = rooms.get(roomNum);
		currRoom.bookRoom(client, date, duration);
    }
    public boolean cancelBooking(int reference){

    }
    public void listBookings(String roomNum){

    }
    public void listBookings(Client client){

    }
    public boolean clientExists(String familyName, String givenName){
        for (Client c: clients
             ) {
			return c.getFamilyName().equals(familyName) && c.getGivenName().equals(givenName);
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

	public void bookingDetails() {
		boolean valid = false;
		boolean hasSmartboard = false;
		String familyName;
		String givenName;
		String input;
		String roomNumber;

		int reqWorkStations = 0;
		int reqBreakoutSeats = 0;
		int duration = 0;

		Client client = null;
		LocalDateTime bookingSlot = LocalDateTime.now();

		do {
			System.out.println("Enter your family name: ");
			valid = false;
			familyName = in.nextLine();
			if (familyName.matches("[A-Z][a-z]*")) {
				valid = true;
			} else {
				valid = false;
				System.out.println("Invalid family name, your name must start with a capital letter. Please try again: ");
			}
		} while (!valid);
		System.out.println("Enter your given name: ");
		do {
			valid = false;
			givenName = in.nextLine();
			if (givenName.matches("[A-Z][a-z]*")) {
				valid = true;
			} else {
				valid = false;
				System.out.println("Invalid given name, your name must start with a capital letter. Please try again: ");
			}
		} while (!valid);

		if (!clientExists(familyName, givenName)) {
			System.out.println("Client does not exist.");
			return;
		} else {
			for (Client c : clients
					) {
				if (c.getFamilyName().equals(familyName) && c.getGivenName().equals(givenName)) {
					client = c;
				}
			}
		}

		System.out.println("Enter the date you want to book the room for in the format YYYY-MM-DD :");
		do {
			valid = false;
			input = in.nextLine();
			if (input.matches("\\d{4}-\\d{2}-\\d{2}")) {
				try {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //document said use YYYY-MM-DD but for some reason that wouldnt work, but yyyy-MM-dd works and thats just the same format with different syntax so whatever should be fine
					format.setLenient(false); //if a date is entered that doesnt exist throw an exception
					Date date = format.parse(input); //date is equal to the input parsed to the format above
					bookingSlot = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()); //convert date to local date time
					if (bookingSlot.isBefore(LocalDate.now().atTime(0, 0, 0))) {
						System.out.println("You cannot enter a date in the past. Please enter a future date: ");
						valid = false;
					} else {
						valid = true;
					}
				} catch (Exception e) { //catch the exception

					e.printStackTrace();//if the exception is thrown it is due to illegal date entered
				}
			} else {
				System.out.println("Incorrect format, please try again: ");
			}
		} while (!valid);

		System.out.println("Enter what time you want to book the room for in the format HH:MM");
		do {
			valid = false;
			input = in.nextLine();
			if (input.matches("^(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]$")) {
				if (bookingSlot.withHour(Integer.parseInt(input.substring(0, 2))).withMinute(Integer.parseInt(input.substring(3, 5))).isBefore(LocalDateTime.now())) {
					System.out.println("You cannot enter a time in the past to book a room, please enter a valid time: ");
					valid = false;
				} else {
					valid = true;
				}
			} else {
				System.out.println("Invalid time entered, please enter a time in the format of HH:MM: ");
				valid = false;
			}
		} while (!valid);

		System.out.println("Enter how long you want to book the room for in hours, whole numbers only please: ");
		do{
			valid = false;
			try{
				duration = in.nextInt();
				valid = true;
			}catch (InputMismatchException e){
				System.out.println("Please enter the number of hours you want to book the room for in hours, whole numbers only please: ");
				valid = false;
				in.nextLine();//flush input
			}
		}while(!valid);

		System.out.println("What type of room do you need?");
		System.out.println("a) Lab");
		System.out.println("b) Meeting");
		System.out.println("c) Conference");
		String choice = in.nextLine();
		char i = choice.charAt(0);
		switch (i){
			case 'a':
				System.out.println("How many work stations do you require: ");
				do {
					valid = false;
					try {
						reqWorkStations = in.nextInt();
						valid = true;
					} catch (InputMismatchException e) {
						System.out.println("Invalid data entered, please enter an integer number: ");
						valid = false;
						in.nextLine(); //flush input
					}
				} while (!valid);
				System.out.println("How many work stations do you require: ");
				do {
					valid = false;
					try {
						reqBreakoutSeats = in.nextInt();
						valid = true;
					} catch (InputMismatchException e) {
						System.out.println("Invalid data entered, please enter an integer number: ");
						valid = false;
						in.nextLine(); //flush input
					}
				} while (!valid);
				for (Room room : rooms.values()
						) {
					if(!(room instanceof Lab)){
						continue;
					}else{
						if(((Lab) room).getWorkstationsCount() >= reqWorkStations && ((Lab) room).getBreakoutCount() >=reqBreakoutSeats && room.isBookable(bookingSlot, duration)){
							bookRoom(client, room.getRoomNum(), bookingSlot, duration);
						}
					}
				}
				break;
			case 'b':
				System.out.println("How many breakout seats do you require: ");
				do {
					valid = false;
					try {
						reqBreakoutSeats = in.nextInt();
						valid = true;
					} catch (InputMismatchException e) {
						System.out.println("Invalid data entered, please enter an integer number: ");
						valid = false;
						in.nextLine(); //flush input
					}
				} while (!valid);
				System.out.println("Do you require a smartboard? Enter Y or N: ");
				do {
					valid = false;

					input = in.nextLine();
					if(input.charAt(0) == 'Y'){
						hasSmartboard = true;
						valid = true;
					}else if(input.charAt(0) =='N'){
						hasSmartboard = false;
						valid = true;
					}else{
						valid = false;
					}

				}while(!valid);
				for (Room room : rooms.values()
						) {
					if (!(room instanceof Conference)) {
						continue;
					} else {
						if(((Conference) room).getBreakoutCount() >= reqBreakoutSeats && ((Conference) room).hasSmartBoard() == hasSmartboard && room.isBookable(bookingSlot, duration)){
							bookRoom(client, room.getRoomNum(), bookingSlot, duration);
						}
					}
				}
			case 'c':
				System.out.println("How many breakout seats do you require: ");
				do {
					valid = false;
					try {
						reqBreakoutSeats = in.nextInt();
						valid = true;
					} catch (InputMismatchException e) {
						System.out.println("Invalid data entered, please enter an integer number: ");
						valid = false;
						in.nextLine(); //flush input
					}
				} while (!valid);
				for (Room room : rooms.values()
						) {
					if (!(room instanceof Meeting)) {
						continue;
					} else {
						if((room).getBreakoutCount() >= reqBreakoutSeats && room.isBookable(bookingSlot, duration)){
							bookRoom(client, room.getRoomNum(), bookingSlot, duration);
						}
					}
				}
		}
    }

	public LinkedList<Client> getClients() {
    	return clients;
	}
}





