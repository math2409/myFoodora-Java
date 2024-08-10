package fr.cs.group15.myFoodora.exception;

public class WrongUserException extends RuntimeException {
	private static final long serialVersionUID = -5409304596044787783L;

	/**
	 * @param message
	 */
	public WrongUserException(String message) {
		super(message);
	}
	
	
}
