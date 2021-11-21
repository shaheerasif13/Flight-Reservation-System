import java.util.ArrayList;

public class Trip {

	private int ID;
	private Flight flight;
	private ArrayList<Passenger> passengers;
	private double totalFare; // Fare of all passengers

	// Default constructor
	public Trip() {
		this.ID = 0;
		this.flight = null;
		this.passengers = null;
		this.totalFare = 0.0;
	}

	// Parameterized constructor
	public Trip(Flight flight, ArrayList<Passenger> passengers, int id) {
		this.ID = id;
		this.flight = flight;
		this.passengers = passengers;

		if (flight != null && this.passengers != null) {
			this.totalFare = this.flight.getFare() * this.passengers.size();
		}

		else {
			this.totalFare = 0.0;
		}
	}

	// *************************
	// Getter and setter methods
	// *************************

	public Flight getFlight() {
		return flight;
	}

	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}

	public double getTotalFare() {
		return totalFare;
	}

	public int getID() {
		return ID;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public void setPassengers(ArrayList<Passenger> passengers) {
		this.passengers = passengers;
	}

	public void setTotalFare(double totalFare) {
		this.totalFare = totalFare;
	}

	public void setID(int iD) {
		ID = iD;
	}

	// ***************
	// Utility methods
	// ***************

	// Method to display trip details
	public void display() {
		System.out.println("Trip ID: " + this.ID + "\n");
		this.flight.display();
		System.out.println("\nFare for " + this.passengers.size() + " passengers: "
				+ (this.flight.getFare() * this.passengers.size()));
	}
}