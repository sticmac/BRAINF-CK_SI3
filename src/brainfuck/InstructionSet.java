package brainfuck;

import java.util.HashMap;
import brainfuck.instructions.*;

public class InstructionSet {
	private final static Instruction[] instructions = {new Incr(), new Decr(), new Right(), new Left()};
	private HashMap<String, Instruction> names;
	private HashMap<Character, Instruction> symbols;

	public InstructionSet() {
		names = new HashMap<>();
		symbols = new HashMap<>();
		for (Instruction instruction: instructions) {
			names.put(instruction.getName(), instruction);
			symbols.put(instruction.getSymbol(), instruction);
		}
	}

	public Instruction getOp(String name) {
		return names.get(name);
	}

	public Instruction getOp(char symbol) {
		return symbols.get(symbol);
	}
}
