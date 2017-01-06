package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.metrics.DataRead;

/**
 * Superclass for all the instructions that will read the memory.
 * Using one of these will then affect the <code>DATA_READ</code> metric.
 *
 * @author JulienLemaire
 */
public class ReadMemory extends Instruction {
	/**
	 * Constructs a <code>ReadMemory</code> instruction with the given name, symbol and color.
	 *
	 * @param name		Instruction's keyword.
	 * @param symbol	Instruction's symbol.
	 * @param color		Instruction's color as an int.
	 */
	public ReadMemory(String name, char symbol, int color) {
		super(name, symbol, color);
	}

	/**
	 * Action performed by the <code>ReadMemory</code> instruction.
	 * Overrides <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html">Consumer</a>'s method.
	 *
	 * @param machine	Virtual Machine whose state will be altered
	 */
	@Override
	public void accept(Machine machine) {
		machine.getMetric(DataRead.class).incr();
	}
}
