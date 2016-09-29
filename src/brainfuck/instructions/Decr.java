package brainfuck.instructions;

import brainfuck.Instruction;
import brainfuck.virtualmachine.Machine;
import brainfuck.virtualmachine.OverflowException;

public class Decr extends Instruction {
	public Decr() {
		super("DECR", '-', "red"); // FIXMETOO
	}

	@Override
	public void accept(Machine machine) throws OverflowException {
		byte value = machine.readMemory();
		if (value <= 0) throw new OverflowException();
		value--;
		machine.writeMemory(value);
	}
}
