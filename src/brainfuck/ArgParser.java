package brainfuck;

/**
 * Parser for the arguments passed through the call of the bfck executable.
 * It also stores the different parsed arguments into fields relative to their function.
 * e.g : if a filename is passed through bfck command, it is stored as a filename in this class.
 * @author Julien Lemaire
 */
public class ArgParser {
	//fields
	private String filename = "";

	//methods
	/**
	 * Main constructor of the <code>ArgParser</code> class.
	 * It takes an array of String, respresenting the arguments, and parse it to store them.
	 * @param args Array of String containing all the arguments passed through the executable.
	 */
	public ArgParser(String[] args) throws SyntaxException {
		for (int i = 0 ; i < args.length ; i++) {
			if (args[i].equals("-p")) {
				if (i+1 < args.length && !(args[i+1].startsWith("-"))) {
					this.filename = args[i+1];
					i++;
				} else {
					throw new SyntaxException("No file for -p option");
				}
			}
		}
	}

	/**
	 * Getter for the filename, if any.
	 * @return The filename if one was passed, else an empty string.
	 */
	public String getFilename() {
		return filename;
	}
}

/**
 * An exception thrown when arguments passed through the bfck executable are not correct.
 * @author Julien Lemaire
 */ 
class SyntaxException extends RuntimeException {
	/**
	 * The main constructor of the <code>SyntaxException</code> class.
	 * @param message The message to display as an error.
	 */
	public SyntaxException(String message) {
		super(message);
	}
}
