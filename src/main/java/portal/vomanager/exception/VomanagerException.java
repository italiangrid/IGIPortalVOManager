package portal.vomanager.exception;

public class VomanagerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5459751610577140555L;
	
	/**
	 * The exception message.
	 */
	private String message;

	/**
	 * The exception constructor.
	 * 
	 * @param message
	 *            - The exception message.
	 */
	public VomanagerException(String message) {
		super();
		this.message = message;
	}

	/**
	 * Get the exception message.
	 * 
	 * @return Return the exception message.
	 */
	public String getMessage() {
		return this.message;
	}

}
