public class DepartureDate {

	private String date;
	private String time;

	// Default constructor
	public DepartureDate() {
		this.date = null;
		this.time = null;
	}

	// Parameterized constructor
	public DepartureDate(String date, String time) {
		this.date = date;
		this.time = time;
	}

	// *************************
	// Getter and setter methods
	// *************************

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return String.format(this.date + " " + this.time);
	}
}