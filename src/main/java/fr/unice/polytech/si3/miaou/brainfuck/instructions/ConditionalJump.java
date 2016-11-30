package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import fr.unice.polytech.si3.miaou.brainfuck.BracketCounter;

/**
 * Describes a conditional jump instruction (Jump or Back).
 */
public abstract class ConditionalJump extends ReadMemory {
	/**
	 * Constructs an conditional instruction with the given name, symbol and color, using Instruction constructor.
	 *
	 * @param name		Instruction's keyword.
	 * @param symbol	Instruction's symbol.
	 * @param color		Instruction's color as an int.
	 */
	public ConditionalJump(String name, char symbol, int color) {
		super(name, symbol, color);
	}

	/**
	 * Called when we need to increment a value in the BracketCounter.
	 *
	 * @param bc	BracketCounter whose counter is to be incremented.
	 */
	abstract public void incr(BracketCounter bc);
}
