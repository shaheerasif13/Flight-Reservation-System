public class Passenger extends Person {

	private String type;

	// Default constructor
	public Passenger() {
		super();
		this.type = null;
	}

	// Parameterized constructor
	public Passenger(String name, String type) {
		super();

		try {
			this.setName(name);
		}

		catch (PersonException.NameException e) {
			e.printStackTrace();
		}

		this.type = type;
	}

	// *************************
	// Getter and setter methods
	// *************************

	public String getType() {
		return type;
	}

	public void setType(String type) throws PassengerException.TypeException {

		if (type == null) {
			throw new PassengerException.TypeException("Passport number can not be null!");
		}

		this.type = type;
	}

	// ***************
	// Utility methods
	// ***************

	@Override
	public String toString() {
		return String.format(this.name + " (" + this.type + ")");
	}
}