package brainfuck;

import java.util.stream.Stream;

import brainfuck.virtualmachine.Machine;

/**
 * Reads the instructions from a Stream and execute them.
 *
 * @author Pierre-Emmanuel Novac
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html">Stream</a>
 * @see Machine
 */
public class Interpreter {
	/**
	 * Stream containing the instructions to parse.
	 */
	private Stream<String> stream;

	/**
	 * Constructs an interpreter using the given Stream.
	 *
	 * @param stream	Stream of lines containing instructions.
	 */
	public Interpreter(Stream<String> stream) {
		this.stream = stream;
	}

	/**
	 * Executes the instructions from the Stream and print memory content if the program terminated successfully.
	 * Exits with error code 38 upon invalid instruction.
	 * Tries to read the line first as short instructions, if an invalid instruction is found, tries to read it as a long instruction.
	 * Not that efficient but should work in most of the cases.
	 *
	 * @param machine	Virtual machine which executes the instructions.
	 */
	public void run(Machine machine) {
		stream.filter(l -> !"".equals(l)).forEachOrdered(line -> {
			boolean validShort = false;
			for (int i = 0; i < line.length() && (validShort = machine.executeOp(line.charAt(i))); i++); // Tries to executes the instructions with the short format
			if (!validShort && !machine.executeOp(line)) { // Tries to execute the whole line (ie. long format)
				System.err.println("Invalid instruction");
				System.exit(38);
			}
		});
		System.out.println(machine.dumpMemory());
	}
}
