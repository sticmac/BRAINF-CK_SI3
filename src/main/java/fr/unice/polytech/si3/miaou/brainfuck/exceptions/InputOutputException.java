package fr.unice.polytech.si3.miaou.brainfuck.exceptions;

import java.io.IOException;

/**
 * Exception thrown when there was an issue reading or writing the the machine's stream.
 *
 * @author Nassim Bounouas
 */
public class InputOutputException extends BrainfuckException {
	/**
	 * Constructs an InputOutputException with given message.
	 *
	 * @param message	message detail.
	 */
	public InputOutputException(String message) {
		super(message);
	}

	/**
	 * Constructs an InputOutputException using the given IOException's message.
	 *
	 * @param e	IOException for which this InputOutputException was thrown.
	 */
	public InputOutputException(IOException e) {
		super(e.getMessage());
	}

	/**
	 * Returns the error code.
	 * The application will exit with this error code.
	 *
	 * @return 8.
	 */
	@Override
	public int getErrorCode() {
		return 8;
	}
}
