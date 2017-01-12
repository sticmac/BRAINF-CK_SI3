package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.OverflowException;
import fr.unice.polytech.si3.miaou.brainfuck.JumpTable;

public class DecrTest {
	Instruction decr;
	Machine machine;


	@Before
	public void setUp() {
		decr = new Decr();
		machine = new Machine(0, new JumpTable());
	}

	@Test
	public void nameTest() {
		assertEquals("DECR", decr.getName());
	}

	@Test
	public void symbolTest() {
		assertEquals('-', decr.getSymbol());
	}

	@Test
	public void colorTest() {
		assertEquals(0xFF4B0082, decr.getColor());
	}

	@Test
	public void acceptTest() {
		machine.writeMemory((byte) (Byte.MIN_VALUE + 1));
		decr.accept(machine);
		assertEquals(Byte.MIN_VALUE, machine.readMemory());
		assertFalse(machine.dumpMetrics().contains("DATA_WRITE: 0"));
	}

	@Test(expected=OverflowException.class)
	public void acceptOverflowTest() {
		decr.accept(machine);
	}
}
