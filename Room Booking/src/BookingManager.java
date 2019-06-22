import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Pattern;

public class BookingManager {
    private int bookingfRef;
    private File dataFile;
    private LinkedList<Client> clients = new LinkedList<>();
    private HashMap<String, Room> rooms = new HashMap<String, Room>();
	Scanner in = new Scanner(System.in);

    public BookingManager(File dataFile) {
        this.dataFile = dataFile;
    }

    public void loadData() {
		try {
			FileInputStream fis = new FileInputStream(dataFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.rooms = (HashMap<String, Room>) ois.readObject();
			ois.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
		public void saveData(File dataFile){
		try{
			FileOutputStream fos = new FileOutputStream(dataFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(this.rooms);
		} catch (IOException e) {

		}
	}
    public boolean addClient(String familyName, String givenName, String email, String number){
        clients.add(new Client(familyName, givenName, email, number));
        return true;
    }
    public String findBooking(LocalDateTime date, int duration, int reference){
		return null;
    }
    public String findBooking(LocalDateTime date, int duration, int reference, int breakoutSeats){
		return null;
    }
    public void bookRoom(Client client, String roomNum, LocalDateTime date, int duration){
		Room currRoom = rooms.get(roomNum);
		currRoom.bookRoom(client, date, duration);
    }
    public boolean cancelBooking(int reference){
		for (Map.Entry<String, Room> entry : rooms.entrySet()) {
			if(entry.getValue().cancelBooking(reference)){
				System.out.println("Booking removed");
				return true;
			}
		}
		System.out.println("No booking matching booking reference.");
		return false;
    }
    public void listBookings(String roomNum){
		System.out.println(rooms.get(roomNum).getBookings());
    }
    public void listBookings(Client client){

    }
    public boolean clientExists(String familyName, String givenName){

        for (Client c: clients
             ) {
			if(c.getFamilyName().equals(familyName) && c.getGivenName().equals(givenName)){
				return true;
			}
        }
        return false;
    }

    public void initialize(){
        loadData();
    }

	public void bookingDetails() {
		boolean valid = false;
		boolean hasSmartboard = false;
		boolean roomFound = false;

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
				bookingSlot = bookingSlot.withHour(Integer.parseInt(input.substring(0, 2))).withMinute(Integer.parseInt(input.substring(3, 5)));
				if (bookingSlot.isBefore(LocalDateTime.now())) {
					System.out.println("You cannot enter a time in the past to book a room, please enter a valid time: ");
					valid = false;
				} else {
					valid = true;
					System.out.println(bookingSlot);
				}
			} else {
				System.out.println("Invalid time entered, please enter a time in the format of HH:MM: ");
				valid = false;
			}
		} while (!valid);

		System.out.println("Enter how long you want to book the room for in hours, whole numbers only please: ");
		do{
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
		System.out.println("b) Conference");
		System.out.println("c) Meeting");
		in.skip(Pattern.compile("\n")); //for some reason in.nextln() was taking in an empty line, by doing this it solves it. I dont know why, but it works
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
				System.out.println("How many seats do you require: ");
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
							roomFound = true;
							System.out.println("Room " + room.getRoomNum() + " has been booked at " + bookingSlot + " for " +duration+ " hours");
							break;
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
						in.nextLine(); //flush input
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
							roomFound = true;
							System.out.println("Room " + room.getRoomNum() + " has been booked at " + bookingSlot + " for " +duration+ " hours");
							break;
						}
					}
				}
				break;
			case 'c':
				System.out.println("How many breakout seats do you require: ");
				do {
					valid = false;
					try {
						reqBreakoutSeats = in.nextInt();
						in.nextLine();//flush input
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
							roomFound = true;
							System.out.println("Room " + room.getRoomNum() + " has been booked at " + bookingSlot + " for " +duration+ " hours");
							break;
						}
					}
				}
				break;
		}
		if(!roomFound){
			System.out.println("Sorry no room could be found");
		}
    }

	public LinkedList<Client> getClients() {
    	return clients;
	}

	public HashMap<String, Room> getRooms() {
		return rooms;
	}
}





