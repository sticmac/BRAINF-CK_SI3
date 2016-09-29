package brainfuck.instructions;

import brainfuck.Instruction;
import brainfuck.virtualmachine.Machine;
import brainfuck.virtualmachine.OverflowException;

/**
 * Incr instruction: increments the current memory cell by one.
 *
 * @author Pierre-Emmanuel Novac
 * @see Instruction
 * @see Machine
 * @see brainfuck.virtualmachine.Memory
 */
public class Incr extends Instruction {
	/**
	 * Constructs the Incr instruction.
	 */
	public Incr() {
		super("INCR", '+', "blue"); // FIXME
	}

	/**
	 * Action performed by the instruction: increment the current memory cell by one.
	 * Overrides <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html">Consumer</a>'s method.
	 *
	 * @param machine	Virtual Machine whose state will be altered
	 * @throws OverflowException	if the current cell value is at it's top limit.
	 */
	@Override
	public void accept(Machine machine) throws OverflowException {
		byte value = machine.readMemory();
		if (value >= 255) throw new OverflowException();
		value++;
		machine.writeMemory(value);
	}
}
