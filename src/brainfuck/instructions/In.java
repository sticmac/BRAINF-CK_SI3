package brainfuck.instructions;

import brainfuck.Instruction;
import brainfuck.virtualmachine.Machine;
import brainfuck.virtualmachine.OverflowException;

/**
 * Read the value present in the input as an ASCII character 
 *
 * @author Nassim Bounouas
 * @see Instruction
 * @see Machine
 * @see brainfuck.virtualmachine.Memory
 */
public class In extends Instruction {
	/**
	 * Constructs the In instruction.
	 */
	public In() {
		super("IN", ',', new int[] {0x00, 0xFF, 0x00});
	}

	/**
	 * Action performed by the instruction: print out the contents of the current memory cell as ASCII.
	 * Overrides <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html">Consumer</a>'s method.
	 *
	 * @param machine	Virtual Machine whose state will be altered
	 */
	@Override
	public void accept(Machine machine) {
	//	byte value = machine.readMemory();
	//	System.out.println(value);	
	}
}
