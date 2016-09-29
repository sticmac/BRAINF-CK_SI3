package brainfuck.virtualmachine;

import brainfuck.InstructionSet;

public class Machine {
	private Memory memory;
	private InstructionSet iset;
	private int location = 0;

	public Machine() {
		memory = new Memory(32); //FIXME
		iset = new InstructionSet();
	}

	public void writeMemory(byte value) {
		memory.set(location, value);
	}

	public byte readMemory() {
		return memory.get(location);
	}

	public void executeOp(String name) throws OverflowException {
		iset.getOp(name).accept(this);
	}

	public void executeOp(char symbol) throws OverflowException {
		iset.getOp(symbol).accept(this);
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int i) throws OutOfMemoryException {
		memory.checkBounds(i);
		location = i;
	}

	public String dumpMemory() {
		return memory.toString();
	}
}
