package brainfuck;

import brainfuck.virtualmachine.Machine;
import brainfuck.virtualmachine.OverflowException;

public class Main {
	public static void main(String[] args) throws OverflowException {

		Machine machine = new Machine();

		machine.executeOp("INCR");
		System.out.println(machine.dumpMemory());

		machine.executeOp('-');
		System.out.println(machine.dumpMemory());

		for (int i = 0; i < 255; i++) {
			machine.executeOp("INCR");
		}
		System.out.println(machine.dumpMemory());
	}
}
