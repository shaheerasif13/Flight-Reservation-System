import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FlightReservationSystem {

	private Scanner scanner;
	private Customer currentCustomer;
	private ArrayList<Customer> customers; // Contains customers (Users) of system
	private ArrayList<Flight> flights; // Contains all flights

	// Default constructor
	public FlightReservationSystem() {
		scanner = new Scanner(System.in);
		customers = new ArrayList<Customer>();
		flights = new ArrayList<Flight>();
	}

	// ***************************
	// Methods for sign up process
	// ***************************

	// Method to sign up (Create customer account in system)
	private boolean signup() {
		scanner.nextLine();
		System.out.println();

		// Creating a new customer
		Customer newCustomer = new Customer();

		// Setting customer CNIC
		boolean success = false;

		while (!success) {
			System.out.print("Enter CNIC: ");
			String CNIC = scanner.nextLine();

			try {
				newCustomer.setCNIC(CNIC);
				success = true;
			}

			catch (CustomerException.CNICException E) {
				System.out.println(E.getMessage());
				success = false;
			}
		}

		// If CNIC is already registered
		if (this.checkCNIC(newCustomer.getCNIC())) {
			System.out.println("\nAccount already exists!");
			return false;
		}

		// If CNIC is not registered
		else {

			// Setting customer name
			success = false;

			while (!success) {
				System.out.print("Enter name: ");
				String name = scanner.nextLine();

				try {
					newCustomer.setName(name);
					success = true;
				}

				catch (CustomerException.NameException E) {
					System.out.println(E.getMessage());
					success = false;
				}
			}

			// Setting gender of customer
			success = false;

			while (!success) {
				System.out.print("Enter gender (M/F): ");
				char gender = scanner.next().charAt(0);

				try {
					newCustomer.setGender(gender);
					success = true;
				}

				catch (CustomerException.GenderException E) {
					System.out.println(E.getMessage());
					success = false;
				}
			}

			// Setting age of customer
			success = false;

			while (!success) {
				System.out.print("Enter age: ");
				int age = scanner.nextInt();

				try {
					newCustomer.setAge(age);
					success = true;
				}

				catch (CustomerException.AgeException E) {
					System.out.println(E.getMessage());
					success = false;
				}
			}

			// Setting address of customer
			success = false;

			while (!success) {
				System.out.print("Enter address: ");
				scanner.nextLine();
				String address = scanner.nextLine();

				try {
					newCustomer.setAddress(address);
					success = true;
				}

				catch (CustomerException.AddressException E) {
					System.out.println(E.getMessage());
					success = false;
				}
			}

			// Setting phone number of customer
			success = false;

			while (!success) {
				System.out.print("Enter phone number: ");
				String phoneNumber = scanner.nextLine();

				try {
					newCustomer.setPhoneNumber(phoneNumber);
					success = true;
				}

				catch (CustomerException.PhoneNumberException E) {
					System.out.println(E.getMessage());
					success = false;
				}
			}

			// Setting username of customer
			while (true) {
				success = false;

				while (!success) {
					System.out.print("Set new username: ");
					String username = scanner.nextLine();

					try {
						newCustomer.setUsername(username);
						success = true;
					}

					catch (CustomerException.UsernameException E) {
						System.out.println(E.getMessage());
						success = false;
					}
				}

				// If username is unique
				if (!this.checkUsername(newCustomer.getUsername())) {
					break;
				}

				System.out.println("Username already exists!");
			}

			// Setting password of customer
			success = false;

			while (!success) {
				System.out.print("Set new password: ");
				String password = scanner.nextLine();

				try {
					newCustomer.setPassword(password);
					success = true;
				}

				catch (CustomerException.PasswordException E) {
					System.out.println(E.getMessage());
					success = false;
				}
			}

			this.customers.add(newCustomer);
			System.out.println("\nAccount created successfully!");
			return true;
		}
	}

	// Method to check CNIC duplication
	private boolean checkCNIC(String CNIC) {

		for (Customer i : this.customers) {

			if (i.getCNIC().equals(CNIC)) {
				return true;
			}
		}

		return false;
	}

	// Method to check user name duplication
	private boolean checkUsername(String username) {

		for (Customer i : this.customers) {

			if (i.getUsername().equals(username)) {
				return true;
			}
		}

		return false;
	}

	// *************************
	// Method for log in process
	// *************************

	private boolean logIn() {
		scanner.nextLine();

		// Taking credentials as input
		System.out.print("\nEnter username: ");
		String username = scanner.nextLine();

		System.out.print("Enter password: ");
		String password = scanner.nextLine();

		// Validating customer credentials
		for (Customer i : this.customers) {

			if (i.getUsername().equals(username) && i.getPassword().equals(password)) {
				this.currentCustomer = i;
				System.out.println("\nAuthenticated!");
				return true;
			}
		}

		System.out.println("\nIncorrect username or password!");
		return false;
	}

	// ******************************************************
	// Methods to read, write, and update in database (Files)
	// ******************************************************

	// Method to load data from database
	private void loadData() {
		this.readCustomersFromFile("Customers.txt");
		this.readFlightsFromFile("Flights.txt");
	}

	// Method to update data in database
	private void updateData() {
		this.writeCustomersToFile("Customers.txt");
		this.writeFlightsToFile("Flights.txt");
	}

	// Method to write customers in file
	private boolean writeCustomersToFile(String filePath) {

		try {
			FileWriter outFile = new FileWriter(filePath, false);

			for (Customer i : this.customers) {
				outFile.write(i.toString());
				outFile.write("\n\n"); // New line after every record
			}

			outFile.close();
			return true;
		}

		catch (IOException E) {
			E.printStackTrace();
			return false;
		}
	}

	// Method to read customers from file
	private boolean readCustomersFromFile(String filePath) {

		try {
			BufferedReader inFile = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String str = "";

			while ((str = inFile.readLine()) != null) {
				String CNIC = str;
				String name = inFile.readLine();
				char gender = inFile.readLine().charAt(0);
				int age = Integer.parseInt(inFile.readLine());
				String address = inFile.readLine();
				String phoneNumber = inFile.readLine();
				String username = inFile.readLine();
				String password = inFile.readLine();

				// Creating and adding a new customer
				this.customers.add(new Customer(CNIC, name, gender, age, address, phoneNumber, username, password));

				// Ignoring newline after every record
				inFile.readLine();
			}

			inFile.close();
			return true;
		}

		catch (IOException E) {
			E.printStackTrace();
			return false;
		}
	}

	// Method to write flights in file
	private boolean writeFlightsToFile(String filePath) {

		try {
			FileWriter outFile = new FileWriter(filePath, false);

			for (Flight i : this.flights) {
				outFile.write(i.toString());
				outFile.write("\n\n"); // New line after every record
			}

			outFile.close();
			return true;
		}

		catch (IOException E) {
			E.printStackTrace();
			return false;
		}
	}

	// Method to read flights from file
	private boolean readFlightsFromFile(String filePath) {

		try {
			BufferedReader inFile = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String str = "";

			while ((str = inFile.readLine()) != null) {
				String flightNumber = str;

				String origin = inFile.readLine();
				String destination = inFile.readLine();

				String departureDate = inFile.readLine();
				String departureTime = inFile.readLine();

				int durationHours = Integer.parseInt(inFile.readLine());
				int durationMinutes = Integer.parseInt(inFile.readLine());

				String airline = inFile.readLine();
				String aircraft = inFile.readLine();
				String cabin = inFile.readLine();

				double fare = Double.parseDouble(inFile.readLine());
				int totalTickets = Integer.parseInt(inFile.readLine());
				int arrivedTickets = Integer.parseInt(inFile.readLine());

				// Creating and adding a new flight
				this.flights.add(new Flight(flightNumber, airline, new Itinerary(origin, destination),
						new DepartureDate(departureDate, departureTime),
						new Duration(durationHours, durationMinutes, 0), aircraft, cabin, fare, totalTickets,
						arrivedTickets));

				// Ignoring newline after every record
				inFile.readLine();
			}

			inFile.close();
			return true;
		}

		catch (IOException E) {
			E.printStackTrace();
			return false;
		}
	}

	// **************************
	// Methods for flight booking
	// **************************

	// Method to book a flight
	private boolean bookFlight() {
		scanner.nextLine();
		System.out.println("\n\t\t\t\t   SEARCH FLIGHTS\n");

		// Origin input
		System.out.print("Departing from: ");
		String origin = scanner.nextLine();

		// Destination input
		System.out.print("Arriving at: ");
		String destination = scanner.nextLine();

		// Departure date input
		System.out.print("Departure date (dd/mm/yyyy): ");
		String date = scanner.nextLine();

		// Passengers data input
		System.out.println("Passengers:");
		System.out.print("Enter number of adults: ");
		int adults = scanner.nextInt();
		System.out.print("Enter number of children: ");
		int children = scanner.nextInt();
		int totalPassengers = adults + children; // Total number of passengers

		// User chooses flight by searching
		Flight chosenFlight = this.chooseFlight(origin, destination, date);

		if (chosenFlight != null) {

			// Checking for seats availability in chosen flight
			while (!chosenFlight.updateTickets(totalPassengers)) {
				System.out.println("\nNot enough tickets!");
				chosenFlight = this.chooseFlight(origin, destination, date);
			}

			// Passenger details input
			ArrayList<Passenger> passengers = this.getPassengersDetail(adults, children);

			// Creating a new trip for customer
			Trip newTrip = new Trip(chosenFlight, passengers, generateTripID());

			// Displaying trip details
			System.out.println("\n\t\t\t\t   TRIP DETAILS\n");
			newTrip.display();

			// Add new trip in customers list
			this.currentCustomer.addTrip(newTrip);
			return true;
		}

		return false;
	}

	// Method for customer to choose desired flight
	private Flight chooseFlight(String origin, String destination, String date) {

		// Searching flights of given parameters
		ArrayList<Flight> targetFlights = this.searchFlights(origin.toLowerCase(), destination.toLowerCase(), date);

		// If no flights of given parameters are found
		if (targetFlights.size() == 0) {
			System.out.println("\nNo flights found!");
			return null;
		}

		// Displaying search results for customer to choose
		System.out.println("\n\t\t\t\t   CHOOSE FLIGHT\n");
		int number = 1;

		for (Flight i : targetFlights) {
			System.out.println("-------------- " + number + " --------------");
			i.display();
			System.out.println();
			number = number + 1;
		}

		System.out.print("Choose flight: ");
		int choice = scanner.nextInt();

		// Validating choice
		while (choice <= 0 || choice > targetFlights.size()) {
			System.out.print("\nInvalid input!\nChoose flight again: ");
			choice = scanner.nextInt();
		}

		return targetFlights.get(choice - 1);
	}

	// Method to search for flights of given parameters
	private ArrayList<Flight> searchFlights(String origin, String destination, String date) {
		ArrayList<Flight> targetFlights = new ArrayList<Flight>();

		for (Flight i : this.flights) {

			if (i.getItinerary().getOrigin().toLowerCase().equals(origin)
					&& i.getItinerary().getDestination().toLowerCase().equals(destination)
					&& i.getDate().getDate().equals(date)) {
				targetFlights.add(i);
			}
		}

		return targetFlights;
	}

	// Method to get passengers detail as input
	private ArrayList<Passenger> getPassengersDetail(int adults, int children) {
		scanner.nextLine();
		System.out.println("\n\t\t\t\tPASSENGERS DETAIL\n");
		ArrayList<Passenger> passengers = new ArrayList<Passenger>();

		int i = 0;

		for (; i < adults; i++) {
			System.out.print("Passenger-" + (i + 1) + " (Adult): ");
			String name = scanner.nextLine();
			passengers.add(new Passenger(name, "Adult"));
		}

		for (; i < adults + children; i++) {
			System.out.print("Passenger-" + (i + 1) + " (Child): ");
			String name = scanner.nextLine();
			passengers.add(new Passenger(name, "Child"));
		}

		return passengers;
	}

	// Method to generate trip id of new trip
	private int generateTripID() {
		Random random = new Random();
		int newTripID = random.ints(10000000, 100000000).findFirst().getAsInt();

		while (!this.isValidTripID(newTripID)) {
			newTripID = random.ints(10000000, 100000000).findFirst().getAsInt();
		}

		return newTripID;
	}

	// Method to validate new trip id
	private boolean isValidTripID(int tripID) {
		ArrayList<Trip> trips = this.currentCustomer.getTrips();

		for (Trip i : trips) {

			if (i.getID() == tripID) {
				return false;
			}
		}

		return true;
	}

	// *************************
	// Method to cancel a flight
	// *************************

	private void cancelFlight() {
		System.out.print("\nEnter trip ID: ");
		int tripID = scanner.nextInt();

		if (this.currentCustomer.deleteTrip(tripID)) {
			System.out.println("\nTrip cancellation successfull!");
		}

		else {
			System.out.println("\nTrip cancellation failed!");
		}
	}

	// **************************************
	// Methods to run main menu functionality
	// **************************************

	// Method to display main menu
	private int showMainMenu() {
		System.out.println("\n\t\t\t\t    MAIN MENU\n");
		System.out.println("1 - Book a flight.");
		System.out.println("2 - Cancel a flight.");
		System.out.println("3 - Log out.\n");
		System.out.print("Enter choice: ");

		int choice = scanner.nextInt();

		// Validating user input
		while (choice < 1 || choice > 3) {
			System.out.print("\nInvalid choice!\nEnter choice again: ");
			choice = scanner.nextInt();
		}

		return choice;
	}

	// Method to run main menu
	private void mainMenu() {
		final int BOOK_FLIGHT = 1;
		final int CANCEL_FLIGHT = 2;
		final int LOG_OUT = 3;
		int choice = -1;

		while (choice != LOG_OUT) {
			choice = this.showMainMenu();

			if (choice == BOOK_FLIGHT) {
				this.bookFlight();
			}

			else if (choice == CANCEL_FLIGHT) {
				this.cancelFlight();
			}

			else if (choice == LOG_OUT) {
				System.out.println("\nLogged out successfully!");
			}
		}
	}

	// ***************************************
	// Methods to run start menu functionality
	// ***************************************

	// Method to display start menu
	private int showStartMenu() {
		System.out.println("\n\t\t\t     FLIGHT RESERVATION SYSTEM\n");
		System.out.println("1 - Log in.");
		System.out.println("2 - Sign up.");
		System.out.println("3 - Exit.\n");
		System.out.print("Enter choice: ");

		int choice = scanner.nextInt();

		// Validating user input
		while (choice < 1 || choice > 3) {
			System.out.print("\nInvalid choice!\nEnter choice again: ");
			choice = scanner.nextInt();
		}

		return choice;
	}

	// Method to run start menu
	private void startMenu() {
		final int LOG_IN = 1;
		final int SIGN_UP = 2;
		final int EXIT = 3;
		int choice = -1;

		while (choice != EXIT) {
			choice = this.showStartMenu();

			if (choice == LOG_IN) {

				if (this.logIn()) {
					this.mainMenu();
				}
			}

			else if (choice == SIGN_UP) {

				if (this.signup()) {
					this.mainMenu();
				}
			}

			else if (choice == EXIT) {
				System.out.println("\nExiting ...");
			}
		}
	}

	// ********************
	// Method to run system
	// ********************

	public void run() {

		// Loading from database
		this.loadData();

		// System starts here
		this.startMenu();

		// Updating the database
		this.updateData();
	}
}