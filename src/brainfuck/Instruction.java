package brainfuck;

import java.util.function.Consumer;

import brainfuck.virtualmachine.Machine;
import brainfuck.virtualmachine.OverflowException;

public abstract class Instruction implements Consumer<Machine> {
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
}
