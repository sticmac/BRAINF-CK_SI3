package brainfuck.instructions;

import brainfuck.Instruction;
import brainfuck.virtualmachine.Machine;
import brainfuck.virtualmachine.OverflowException;

public class Incr extends Instruction {
	public Incr() {
		super("INCR", '+', "blue"); // FIXME
	}

	@Override
	public void action(Machine machine) throws OverflowException {
		byte value = machine.readMemory();
		if (value >= 255) throw new OverflowException();
		value++;
		machine.writeMemory(value);
	}
}
