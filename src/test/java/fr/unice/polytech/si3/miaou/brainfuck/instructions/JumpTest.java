package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.JumpTable;

public class JumpTest {
	ConditionalJump jump;
	Machine machine;
	boolean notMatching = false;

	@Before
	public void setUp() {
		jump = new Jump();
		JumpTable jt = new JumpTable();
		jt.bind(jump, 0);
		jt.bind(new Back(), 1);
		machine = new Machine(0, jt);
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

	@Test
	public void acceptDoNothingTest() {
		machine.writeMemory((byte) 1);
		jump.accept(machine);
		assertEquals(0, machine.getInstrPointer());
	}

	@Test
	public void acceptTest() {
		jump.accept(machine);
		assertEquals(1, machine.getInstrPointer());
		assertFalse(machine.dumpMetrics().contains("DATA_READ: 0"));
	}
}
