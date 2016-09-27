package brainfuck;

import brainfuck.virtualmachine.Machine;
import brainfuck.virtualmachine.OverflowException;

public abstract class Instruction {
	private String name;
	private char symbol;
	private String color;

	public Instruction(String name, char symbol, String color) {
		this.name = name;
		this.symbol = symbol;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public char getSymbol() {
		return symbol;
	}

	public String getColor() {
		return color;
	}

	abstract public void action(Machine machine) throws OverflowException;
}
