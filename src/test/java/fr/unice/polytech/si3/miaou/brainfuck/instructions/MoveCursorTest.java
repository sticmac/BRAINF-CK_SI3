package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.JumpTable;

public class MoveCursorTest {
	MoveCursor mc;
	Machine machine;

	@Before
	public void setUp() {
		mc = new MoveCursor("", '\0', 0);
		JumpTable jt = new JumpTable();
		machine = new Machine(0, jt);
	}

	@Test
	public void acceptTest() {
		assertTrue(machine.dumpMetrics().contains("DATA_MOVE: 0"));
		mc.accept(machine);
		assertTrue(machine.dumpMetrics().contains("DATA_MOVE: 1"));
	}
}
