package mobi.sherif.animation.exception;

/**
 * An {@link Exception} thrown when an error occurs with a Vibration Pattern.
 * @author Sherif elKhatib
 *
 */
public class UnknownVibrationPattern extends Exception {
	private static final long serialVersionUID = -4888643076682843066L;
	public static String UNKNOWN = "The vibration pattern is unrecognized.";
	public UnknownVibrationPattern(String message) {
		super(message);
	}
}
