public class Duration {

	private int hours;
	private int minutes;
	private int seconds;

	// Default constructor
	public Duration() {
		this.hours = 0;
		this.minutes = 0;
		this.seconds = 0;
	}

	// Parameterized constructor
	public Duration(int hours, int minutes, int seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	// *************************
	// Getter and setter methods
	// *************************

	public int getHours() {
		return hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	@Override
	public String toString() {
		return String.format(this.hours + "h" + this.minutes);
	}
}