package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.Metrics;
import fr.unice.polytech.si3.miaou.brainfuck.JumpTable;

public class BackTest {
	ConditionalJump back;
	Machine machine;
	boolean notMatching = false;

	@Before
	public void setUp() {
		back = new Back();
		machine = new Machine(0, new JumpTable());
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

/*	@Ignore
	@Test
	public void acceptDoNothingTest() {
		back.accept(machine);
		assertFalse(machine.isJumping());
	}

	@Ignore
	@Test
	public void acceptTest() {
		machine.writeMemory((byte) 1);
		back.accept(machine);
		assertTrue(machine.isJumping());
		assertTrue(0 != Metrics.DATA_READ.value());
	}

	@Ignore
	@Test
	public void acceptThenResetTest() {
		machine.writeMemory((byte) 1);
		back.accept(machine);
		back.accept(machine);
		assertFalse(machine.isJumping());
	}

	@Ignore
	@Test
	public void acceptResetTest() {
		machine.setJumping(true);
		back.accept(machine);
		assertFalse(machine.isJumping());
	}
*/
}
