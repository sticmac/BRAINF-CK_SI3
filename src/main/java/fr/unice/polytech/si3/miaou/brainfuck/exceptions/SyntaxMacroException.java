package fr.unice.polytech.si3.miaou.brainfuck.exceptions;

/**
 * An exception thrown when Macro definition structure is not used correctly.
 *
 * @author Julien Lemaire
 */
public class SyntaxMacroException extends BrainfuckException {
	/**
	 * The main constructor of the <code>SyntaxMacroException</code> class.
	 * @param message The message to display as an error.
	 */
	public SyntaxMacroException(String message) {
		super(message);
	}

	/**
	 * Returns the error code 10 for notifying an error in syntax when defining a Macro.
	 * The application will exit with this error code.
	 *
	 * @return 10.
	 */
	@Override
	public int getErrorCode() {
		return 10;
	}
}
