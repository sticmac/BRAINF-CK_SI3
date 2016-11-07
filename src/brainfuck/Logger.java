package brainfuck;

import java.io.IOException;

import brainfuck.io.WriteTextFile;
import brainfuck.instructions.Instruction;
import brainfuck.virtualmachine.Machine;

/**
 * Storing all the data for a proper logging, if the user wanted some.
 *
 * @author Julien Lemaire
 */
public class Logger {
	/**
	 * The log file.
	 */
	private WriteTextFile wtf;

	/**
	 * Execution step number.
	 */
	private int counter;

	/**
	 * Main constructor of the <code>Logger</code> class.
	 *
	 * @param filename the name of the program file.
	 * @throws IOException	if it's impossible to create the log file.
	 */
	public Logger(String filename) throws IOException {
		wtf = new WriteTextFile(filename.substring(0, filename.lastIndexOf("."))+".log");
	}

	/**
	 * Writing formatted data into the log file.
	 *
	 * @param exec location of execution pointer.
	 * @param instruction the previously executed instruction.
	 * @param machine the current instance of the virtual machine.
	 * @throws IOException in case writing in the file is impossible.
	 */
	public void logStep(int exec, Instruction instruction, Machine machine) throws IOException {
		this.write((++counter) + " — exec " + exec + ": "
			+ instruction.getName() + " on C" + machine.getLocation() + "\n"
			+ machine.dumpMemory());
	}

	/**
	 * Writing data in the log file.
	 *
	 * @param data data to write in the log file.
	 * @throws IOException	in case writing in the file is impossible.
	 */
	public void write(String data) throws IOException {
		wtf.write(data);
	}
}
