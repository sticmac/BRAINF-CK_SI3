package fr.unice.polytech.si3.miaou.brainfuck.exceptions;

/**
 * An exception thrown when the language argument passed through the bfck executable is not correct.
 *
 * @author Guillaume Casagrande
 */
public class LanguageException extends BrainfuckException {
	/**
	 * The main constructor of the <code>LanguageException</code> class.
	 * @param message The message to display as an error.
	 */
	public LanguageException(String message) {
		super(message);
	}

	/**
	 * Returns the error code 13 for notifying an error in the command line arguments.
	 * The application will exit with this error code.
	 *
	 * @return 13.
	 */
	@Override
	public int getErrorCode() {
		return 13;
	}
}
