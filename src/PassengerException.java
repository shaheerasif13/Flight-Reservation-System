public class PassengerException extends PersonException {

	// Passport number exceptions
	public static class TypeException extends BaseException {

		public TypeException(String message) {
			super(message);
		}
	}
}