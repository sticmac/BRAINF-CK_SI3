package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.metrics.DataWrite;

/**
 * Superclass for all the instructions that will modify the memory.
 *
 * @author Julien Lemaire
 */
public class WriteMemory extends Instruction {
	/**
	 * Constructs a <code>WriteMemory</code> instruction with the given name, symbol and color.
	 *
	 * @param name		Instruction's keyword.
	 * @param symbol	Instruction's symbol.
	 * @param color		Instruction's color as an int.
	 */
	public WriteMemory(String name, char symbol, int color, String commandInC) {
		super(name, symbol, color, commandInC);
	}

	/**
	 * Action performed by the <code>WriteMemory</code> instruction.
	 * Overrides <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html">Consumer</a>'s method.
	 *
	 * @param machine	Virtual Machine whose state will be altered
	 */
	@Override
	public void accept(Machine machine) {
		machine.getMetric(DataWrite.class).incr();
	}
}
