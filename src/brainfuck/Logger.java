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
	 * The log file.
	 */
	private WriteTextFile wtf;

	/**
	 * Main constructor of the <code>Logger</code> class.
	 *
	 * @param logName	the name of the log file.
	 * @throws IOException	if it's impossible to create the log file.
	 */
	public Logger(String logName) throws IOException {
		wtf = new WriteTextFile(logName);
	}

	/**
	 * Writing data in the log file.
	 * @param data data to write in the log file.
	 * @throws IOException	in case writing the file is impossible.
	 */
	public void write(String data) throws IOException {
		wtf.write(data);
	}
}
