package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;

/**
 * Return instruction
 *
 * @author Julien Lemaire
 */
public class Return extends Instruction {
	/**
	 * Constructs the Jump instruction.
	 */
	public Return() {
		super("RET", 'R', 0);
	}

	/**
	 * Action performed by the instruction: returns to the location after the previous procedure call.
	 * Overrides <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html">Consumer</a>'s method.
	 *
	 * @param machine	Virtual Machine whose state will be altered
	 */
	@Override
	public void accept(Machine machine) {
		machine.goToLastReturnAddress();
	}
}
