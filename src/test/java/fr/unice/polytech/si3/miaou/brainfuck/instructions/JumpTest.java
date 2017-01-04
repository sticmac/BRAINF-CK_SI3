package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.Metrics;
import fr.unice.polytech.si3.miaou.brainfuck.JumpTable;

public class JumpTest {
	ConditionalJump jump;
	Machine machine;
	boolean notMatching = false;

	@Before
	public void setUp() {
		jump = new Jump();
		machine = new Machine(0, new JumpTable());
	}

	@Test
	public void nameTest() {
		assertEquals("JUMP", jump.getName());
	}

	@Test
	public void symbolTest() {
		assertEquals('[', jump.getSymbol());
	}

	@Test
	public void colorTest() {
		assertEquals(0xFFFF7F00, jump.getColor());
	}

/*	@Ignore
	@Test
	public void acceptDoNothingTest() {
		machine.writeMemory((byte) 1);
		jump.accept(machine);
		assertFalse(machine.isJumping());
	}

	@Ignore
	@Test
	public void acceptTest() {
		jump.accept(machine);
		assertTrue(machine.isJumping());
		assertTrue(0 != Metrics.DATA_READ.value());
	}

	@Ignore
	@Test
	public void acceptThenResetTest() {
		jump.accept(machine);
		jump.accept(machine);
		assertFalse(machine.isJumping());
	}

	@Ignore
	@Test
	public void acceptResetTest() {
		machine.writeMemory((byte) 1);
		machine.setJumping(true);
		jump.accept(machine);
		assertFalse(machine.isJumping());
	}
*/
}
