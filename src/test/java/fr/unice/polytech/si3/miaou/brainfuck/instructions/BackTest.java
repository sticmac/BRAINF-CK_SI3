package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.JumpTable;

public class BackTest {
	ConditionalJump back;
	Machine machine;
	boolean notMatching = false;

	@Before
	public void setUp() {
		back = new Back();
		JumpTable jt = new JumpTable();
		jt.bind(new Jump(), 0);
		jt.bind(back, 1);
		machine = new Machine(0, jt);
	}

	@Test
	public void nameTest() {
		assertEquals("BACK", back.getName());
	}

	@Test
	public void symbolTest() {
		assertEquals(']', back.getSymbol());
	}

	@Test
	public void colorTest() {
		assertEquals(0xFFFF0000, back.getColor());
	}

	@Test
	public void acceptDoNothingTest() {
		back.accept(machine);
		assertEquals(0, machine.getInstrPointer());
	}

	@Test
	public void acceptTest() {
		machine.writeMemory((byte) 1);
		back.accept(machine);
		assertEquals(1, machine.getInstrPointer());
		assertFalse(machine.dumpMetrics().contains("DATA_READ: 0"));
	}
}
