package brainfuck;

import java.util.stream.Stream;
import java.io.IOException;
import brainfuck.io.WriteTextFile;

/**
 * Storing all the data for a proper logging, if the user wanted some.
 * @author Julien Lemaire
 */
public class Logger {
	/**
	 * Containing log data.
	 */
	private StringBuilder data;

	/**
	 * Name of the log file.
	 */
	private String logName;

	/**
	 * Main constructor of the <code>Logger</code> class.
	 *
	 * @param logName	the name of the log file.
	 * @param data		log data, as a <code>StringBuilder</code>.
	 */
	public Logger(String logName, StringBuilder data) {
		this.logName = logName;
		this.data = data;
	}
	
	/**
	 * Constructor of the <code>Logger</code> class with empty data.
	 *
	 * @param logName	the name of the log file.
	 */
	public Logger(String logName) {
		this(logName, new StringBuilder());
	}

	/**
	 * Adding a line for data logging.
	 *
	 * @param line new line to add to logging.
	 */
	public void add(String line) {
		data.append(line);
	}

	/**
	 * Getting for the log data.
	 *
	 * @return the log data, as a <code>String</code>.
	 */
	public String getData() {
		return data.toString();
	}

	/**
	 * Writing the log file.
	 * @throws IOException 	in case writing the file is impossible.
	 */
	public void write() throws IOException {
		new WriteTextFile(logName).write(data.toString());
	}
}
