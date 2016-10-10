package brainfuck;

import java.util.List;

import brainfuck.virtualmachine.Machine;

/**
 * Reads the instructions from a List and execute them.
 *
 * @author Pierre-Emmanuel Novac
 * @see Machine
 */
public class Interpreter {
	/**
	 * List containing the instructions to parse.
	 */
	private List<Instruction> instructions;

	/**
	 * Constructs an interpreter using the given List of Instruction.
	 *
	 * @param stream	List of Instruction containing instructions to execute.
	 */
	public Interpreter(List<Instruction> instructions) {
		this.instructions = instructions;
	}

	/**
	 * Executes the instructions from the List and print memory content if the program terminated successfully.
	 *
	 * @param machine	Virtual machine which executes the instructions.
	 */
	public void run(Machine machine) {
		int i = 0;
		while (i >= 0 && i < instructions.size()) {
			machine.executeOp(instructions.get(i));
			if (machine.isReversed()) i--;
			else i++;
		}
		System.out.print(machine.dumpMemory());
	}
}
