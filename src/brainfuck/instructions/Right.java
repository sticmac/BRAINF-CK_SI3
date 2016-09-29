package brainfuck.instructions;

import brainfuck.Instruction;
import brainfuck.virtualmachine.Machine;
import brainfuck.virtualmachine.OutOfMemoryException;

public class Right extends Instruction {
	public Right() {
		super("RIGHT", '>', "pink"); // FIXMETOO
	}

	@Override
	public void action(Machine machine) throws OutOfMemoryException {
		int location = machine.getLocation();
		location++;
		machine.setLocation(location);
	}
}
