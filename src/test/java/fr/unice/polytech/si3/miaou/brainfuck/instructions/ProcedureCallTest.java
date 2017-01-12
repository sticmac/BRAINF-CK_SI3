package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import fr.unice.polytech.si3.miaou.brainfuck.JumpTable;
import fr.unice.polytech.si3.miaou.brainfuck.Procedure;
import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProcedureCallTest {
	private ProcedureCall procedure;
	private Machine machine;

	@Before
	public void defineContext() {
		machine = new Machine(1, new JumpTable());
		procedure = new ProcedureCall(new Procedure("Miaou", 0));
	}

	@Test
	public void testNoParameter() {
		procedure.accept(machine);
		assertEquals(0, machine.getLocation());
	}

	@Test
	public void testWithParameter() {
		procedure = new ProcedureCall(new Procedure("Miaou", 0), 38);
		procedure.accept(machine);
		assertEquals(38, machine.getLocation());
	}
}
