package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.OverflowException;
import fr.unice.polytech.si3.miaou.brainfuck.JumpTable;

public class IncrTest {
	Instruction incr;
	Machine machine;

	@Before
	public void setUp() {
		incr = new Incr();
		machine = new Machine(0, new JumpTable());
	}

	@Test
	public void nameTest() {
		assertEquals("INCR", incr.getName());
	}

	@Test
	public void symbolTest() {
		assertEquals('+', incr.getSymbol());
	}

	@Test
	public void colorTest() {
		assertEquals(0xFFFFFFFF, incr.getColor());
	}

	@Test
	public void acceptTest() {
		incr.accept(machine);
		assertEquals(Byte.MIN_VALUE + 1, machine.readMemory());
		assertFalse(machine.dumpMetrics().contains("DATA_WRITE: 0"));
	}

	@Test(expected=OverflowException.class)
	public void acceptOverflowTest() {
		machine.writeMemory(Byte.MAX_VALUE);
		incr.accept(machine);
	}
}
