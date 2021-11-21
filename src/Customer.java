import java.util.ArrayList;

public class Customer extends Person {

	private String username;
	private String password;
	private ArrayList<Trip> trips;

	// Default constructor
	public Customer() {
		super();
		this.username = null;
		this.password = null;
		this.trips = null;
	}

	// Parameterized constructor
	public Customer(String CNIC, String name, char gender, int age, String address, String phoneNumber, String username,
			String password) {
		super(CNIC, name, gender, age, address, phoneNumber);
		this.username = username;
		this.password = password;
		this.trips = new ArrayList<Trip>();
	}

	// *************************
	// Getter and setter methods
	// *************************

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public ArrayList<Trip> getTrips() {
		return trips;
	}

	public void setUsername(String username) throws CustomerException.UsernameException {

		if (username == null) {
			throw new CustomerException.UsernameException("Username can not be null!");
		}

		else if (username.length() < 5) {
			throw new CustomerException.UsernameException("Username too short!");
		}

		else if (username.length() > 15) {
			throw new CustomerException.UsernameException("Username too long!");
		}

		this.username = username;
	}

	public void setPassword(String password) throws CustomerException.PasswordException {

		if (password == null) {
			throw new CustomerException.PasswordException("Password can not be null!");
		}

		else if (password.length() < 8) {
			throw new CustomerException.PasswordException("Password too short!");
		}

		this.password = password;
	}

	public void setTrips(ArrayList<Trip> trips) {
		this.trips = trips;
	}

	// ***************
	// Utility methods
	// ***************

	// Method to add trip of customer
	public void addTrip(Trip newTrip) {
		this.trips.add(newTrip);
	}

	// Method to delete trip of customer
	public boolean deleteTrip(int targetID) {

		for (Trip i : this.trips) {

			if (i.getID() == targetID) {
				this.trips.remove(i);
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return String.format(super.toString() + "\n" + this.username + "\n" + this.password);
	}
}