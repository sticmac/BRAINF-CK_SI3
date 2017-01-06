package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.JumpTable;

public class ReadMemoryTest {
	ReadMemory rm;
	Machine machine;

	@Before
	public void setUp() {
		rm = new ReadMemory("", '\0', 0);
		JumpTable jt = new JumpTable();
		machine = new Machine(0, jt);
	}

	@Test
	public void acceptTest() {
		assertTrue(machine.dumpMetrics().contains("DATA_READ: 0"));
		rm.accept(machine);
		assertTrue(machine.dumpMetrics().contains("DATA_READ: 1"));
	}
}
