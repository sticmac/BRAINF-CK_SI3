package brainfuck.instructions;

import brainfuck.Instruction;
import brainfuck.virtualmachine.Machine;
import brainfuck.virtualmachine.OutOfMemoryException;

public class Left extends Instruction {
	public Left() {
		super("LEFT", '<', "yellow"); // FIXMETOO
	}

	@Override
	public void accept(Machine machine) throws OutOfMemoryException {
		int location = machine.getLocation();
		location--;
		machine.setLocation(location);
	}
}
