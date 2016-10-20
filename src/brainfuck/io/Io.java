package brainfuck.io;

import java.io.*;
import brainfuck.exceptions.InputOutputException;
import brainfuck.exceptions.InputFileNotFoundException;

/**
 * Manage Machine's input and output flows.
 *
 * @author Nassim Bounouas
 */
public class Io {

	/**
	 * InputStream used as input flow.
	 */
	private InputStream input = System.in;

	/**
	 * OutputStream used as output flow.
	 */
	private OutputStream output = System.out;

	/**
	 * Constructs an Io Object used to access in read or write.
	 *
	 * @param in	filename to use for input. Set to null to use System.in.
	 * @param out	filename to use for output. Set to null to use System.out.
	 * @throws FileNotFoundException	if there was an issue when opening the output file.
	 * @throws InputFileNotFoundException	if there was an issue when opening the input file.
	 */
	public Io(String in, String out) throws FileNotFoundException {
		if(in != null) {
			try {
				this.input = new FileInputStream(in);
			} catch(FileNotFoundException e) {
				throw new InputFileNotFoundException(e);
			}
		}

		if(out != null) {
			this.output = new FileOutputStream(out, false); // replace mode
		}
	}

	/**
	 * Get the next input value.
	 *
	 * @return value read from input.
	 * @throws InputOutputException if there was an issue while reading the value.
	 */
	public int read() {
		try {
			return this.input.read();
		} catch (IOException e) {
			throw new InputOutputException(e);
		}
	}

	/**
	 * Output a value.
	 *
	 * @param c	value to write to output.
	 * @throws InputOutputException	if there was an issue while writing the value.
	 */
	public void write(int c) {
		try {
			this.output.write(c);
		} catch (IOException e) {
			throw new InputOutputException(e);
		}
	}

}
