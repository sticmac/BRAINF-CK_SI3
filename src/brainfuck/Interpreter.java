package brainfuck;

import java.util.stream.Stream;

import java.util.List;

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
	private List<Instruction> instructions;

	/**
	 * Constructs an interpreter using the given Stream.
	 *
	 * @param stream	Stream of lines containing instructions.
	 */
	public Interpreter(List<Instruction> instructions) {
		this.instructions = instructions;
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
		instructions.forEach(machine::executeOp);
		System.out.print(machine.dumpMemory());
	}
}
