public class Itinerary {

	private String origin;
	private String destination;

	// Default constructor
	public Itinerary() {
		this.origin = null;
		this.destination = null;
	}

	// Parameterized constructor
	public Itinerary(String origin, String destination) {
		this.origin = origin;
		this.destination = destination;
	}

	// *************************
	// Getter and setter methods
	// *************************

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		return String.format(this.origin + " - " + this.destination);
	}
}