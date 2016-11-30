package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import fr.unice.polytech.si3.miaou.brainfuck.Metrics;
import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;

/**
 * Superclass for all the instructions that will move the cursor into the memory.
 *
 * @author Julien Lemaire
 */
public class MoveCursor extends Instruction {
	/**
	 * Constructs a <code>MoveCursor</code> instruction with the given name, symbol and color.
	 *
	 * @param name		Instruction's keyword.
	 * @param symbol	Instruction's symbol.
	 * @param color		Instruction's color as an int.
	 */
	public MoveCursor(String name, char symbol, int color) {
		super(name, symbol, color);
	}

	/**
	 * Action performed by the <code>MoveCursor</code> instruction.
	 * Overrides <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html">Consumer</a>'s method.
	 *
	 * @param machine	Virtual Machine whose state will be altered
	 */
	@Override
	public void accept(Machine machine) {
                Metrics.DATA_MOVE.incr();
	}
}
