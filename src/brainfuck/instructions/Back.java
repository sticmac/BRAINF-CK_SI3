package brainfuck.instructions;

import brainfuck.Instruction;
import brainfuck.BracketCounter;
import brainfuck.virtualmachine.Machine;
import brainfuck.virtualmachine.OutOfMemoryException;

/**
 * Back instruction: go back to the instruction right after the associated JUMP if the pointed
 * memory cell is not equals to zero.
 *
 * @author Pierre-Emmanuel Novac
 * @see Instruction
 * @see Machine
 * @see brainfuck.virtualmachine.Memory
 */
public class Back extends Instruction implements ConditionalJump {
	/**
	 * Constructs the Back instruction.
	 */
	public Back() {
		super("BACK", ']', 0xFF9400D3);
	}


	/**
	 * Action performed by the instruction: conditional loop.
	 * Overrides <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html">Consumer</a>'s method.
	 *
	 * @param machine	Virtual Machine whose state will be altered
	 */
	@Override
	public void accept(Machine machine) {
		if (!machine.isJumping() && machine.readMemory() != Byte.MIN_VALUE) {
			machine.setJumping(true);
			machine.setReversed(true);
		} else {
			machine.setJumping(false);
		}
	}

	@Override
	public void incr(BracketCounter bc) {
		bc.incrRight();
	}
}