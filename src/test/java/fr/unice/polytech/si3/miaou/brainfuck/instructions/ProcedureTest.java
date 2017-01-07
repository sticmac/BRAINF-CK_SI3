package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.OverflowException;
import fr.unice.polytech.si3.miaou.brainfuck.JumpTable;

public class ProcedureTest {
	Instruction proc;
	Machine machine;

	@Before
	public void setUp() {
		proc = new Procedure("test", 10);
		machine = new Machine(0, new JumpTable());
	}

	@Test
	public void nameTest() {
		assertEquals("test", proc.getName());
	}

	@Test
	public void symbolTest() {
		assertEquals('\0', proc.getSymbol());
	}

	@Test
	public void colorTest() {
		assertEquals(0, proc.getColor());
	}

	@Test
	public void acceptTest() {
		proc.accept(machine);
		assertEquals(9, machine.getInstrPointer());
		machine.goToLastReturnAddress();
		assertEquals(0, machine.getInstrPointer());
	}
}
