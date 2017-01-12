package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.JumpTable;

public class RightTest {
	Instruction right;
	Machine machine;

	@Before
	public void setUp() {
		right = new Right();
		machine = new Machine(0, new JumpTable());
	}

	@Test
	public void nameTest() {
		assertEquals("RIGHT", right.getName());
	}

	@Test
	public void symbolTest() {
		assertEquals('>', right.getSymbol());
	}

	@Test
	public void colorTest() {
		assertEquals(0xFF0000FF, right.getColor());
	}

	@Test
	public void acceptTest() {
		right.accept(machine);
		assertEquals(1, machine.getLocation());
		assertFalse(machine.dumpMetrics().contains("DATA_MOVE: 0"));
	}
}
