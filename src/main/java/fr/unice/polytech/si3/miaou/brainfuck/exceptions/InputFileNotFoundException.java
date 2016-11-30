package fr.unice.polytech.si3.miaou.brainfuck.exceptions;

import java.io.FileNotFoundException;

/**
 * Exception thrown when the file specified for input stream does not exist.
 *
 * @author Nassim Bounouas
 */
public class InputFileNotFoundException extends BrainfuckException {
	/**
	 * Constructs an InputFileNotFoundException using the message from the given FileNotFoundException.
	 *
	 * @param e	FileNotFoundException for which this InputFileNotFoundException was thrown.
	 */
	public InputFileNotFoundException(FileNotFoundException e) {
		super(e.getMessage());
	}

	/**
	 * Returns the error code.
	 * The application will exit with this error code.
	 *
	 * @return 3.
	 */
	@Override
	public int getErrorCode() {
		return 3;
	}
}
