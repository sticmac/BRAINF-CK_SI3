package fr.unice.polytech.si3.miaou.brainfuck.exceptions;

import java.io.FileNotFoundException;

/**
 * Exception thrown when the file specified for output stream does not exist.
 *
 * @author Nassim Bounouas
 */
public class OutputFileNotFoundException extends BrainfuckException {
	/**
	 * Constructs an OutputFileNotFoundException using the message from the given FileNotFoundException.
	 *
	 * @param e	FileNotFoundException for which this OutputFileNotFoundException was thrown.
	 */
	public OutputFileNotFoundException(FileNotFoundException e) {
		super(e.getMessage());
	}

	/**
	 * Returns the error code.
	 * The application will exit with this error code.
	 *
	 * @return 9.
	 */
	@Override
	public int getErrorCode() {
		return 9;
	}
}
