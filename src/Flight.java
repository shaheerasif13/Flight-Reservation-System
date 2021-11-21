public class Flight {

	private String flightNumber;
	private String airline; // Can be made as class (Having name and number)
	private Itinerary itinerary;
	private DepartureDate date;
	private Duration duration;
	private String aircraft; // Can be made as class (Having name and number)
	private String cabin; // Can be made as class (Having categories for each cabin)
	private double fare;
	private int totalTickets;
	private int arrivedTickets;

	// Default constructor
	public Flight() {
		this.flightNumber = null;
		this.airline = null;
		this.itinerary = null;
		this.date = null;
		this.duration = null;
		this.aircraft = null;
		this.cabin = null;
		this.fare = 0.0;
		this.totalTickets = 0;
		this.arrivedTickets = 0;
	}

	// Parameterized constructor
	public Flight(String flightNumber, String airline, Itinerary itinerary, DepartureDate date, Duration duration,
			String aircraft, String cabin, double fare, int totalTickets, int arrivedTickets) {
		this.flightNumber = flightNumber;
		this.airline = airline;
		this.itinerary = itinerary;
		this.date = date;
		this.duration = duration;
		this.aircraft = aircraft;
		this.cabin = cabin;
		this.fare = fare;
		this.totalTickets = totalTickets;
		this.arrivedTickets = arrivedTickets;
	}

	// *************************
	// Getter and setter methods
	// *************************

	public String getFlightNumber() {
		return flightNumber;
	}

	public String getAirline() {
		return airline;
	}

	public Itinerary getItinerary() {
		return itinerary;
	}

	public DepartureDate getDate() {
		return date;
	}

	public Duration getDuration() {
		return duration;
	}

	public String getAircraft() {
		return aircraft;
	}

	public String getCabin() {
		return cabin;
	}

	public double getFare() {
		return fare;
	}

	public int getTotalTickets() {
		return totalTickets;
	}

	public int getArrivedTickets() {
		return arrivedTickets;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}

	public void setDate(DepartureDate date) {
		this.date = date;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public void setTotalTickets(int numberOfTickets) {
		this.totalTickets = numberOfTickets;
	}

	public void setArrivedTickets(int arrivedTickets) {
		this.arrivedTickets = arrivedTickets;
	}

	// ***************
	// Utility methods
	// ***************

	// Method to update tickets on passengers arrival
	public boolean updateTickets(int totalPassengers) {

		if ((this.arrivedTickets + totalPassengers) > this.totalTickets) {
			return false;
		}

		this.arrivedTickets = this.arrivedTickets + totalPassengers;
		return true;
	}

	// Method to get remaining tickets
	public int getRemainingTickets() {
		return (this.totalTickets - this.arrivedTickets);
	}

	// Method to display flight details
	public void display() {
		System.out.println(String.format(this.itinerary.toString() + "\n" + this.date.toString() + "\nFlight duration: "
				+ this.duration.toString() + "\nOperated by " + this.airline + ", " + this.flightNumber + "\nAircraft "
				+ this.aircraft + "\nCabin " + this.cabin + "\nFare: " + this.fare));
	}

	@Override
	public String toString() {
		return String
				.format(this.flightNumber + "\n" + this.itinerary.getOrigin() + "\n" + this.itinerary.getDestination()
						+ "\n" + this.date.getDate() + "\n" + this.date.getTime() + "\n" + this.duration.getHours()
						+ "\n" + this.duration.getMinutes() + "\n" + this.airline + "\n" + this.aircraft + "\n"
						+ this.cabin + "\n" + this.fare + "\n" + this.totalTickets + "\n" + this.arrivedTickets);
	}
}