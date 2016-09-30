package brainfuck;

import brainfuck.virtualmachine.Machine;
import brainfuck.virtualmachine.OverflowException;

public class Main {
	public static void main(String[] args) throws OverflowException {
		//InstructionList ilist = new InstructionList();
		Machine machine = new Machine();
		ArgParser parser = new ArgParser(args);

		machine.executeOp("INCR");
		System.out.print(machine.dumpMemory());
		machine.executeOp('-');
		System.out.print(machine.dumpMemory());
	}
}
