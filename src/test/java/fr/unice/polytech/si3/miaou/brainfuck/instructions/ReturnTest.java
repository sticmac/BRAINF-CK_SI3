package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.OverflowException;
import fr.unice.polytech.si3.miaou.brainfuck.JumpTable;

public class ReturnTest {
	Instruction ret;
	Machine machine;

	@Before
	public void setUp() {
		ret = new Return();
		machine = new Machine(0, new JumpTable());
	}

	@Test
	public void nameTest() {
		assertEquals("RET", ret.getName());
	}

	@Test
	public void symbolTest() {
		assertEquals('R', ret.getSymbol());
	}

	@Test
	public void colorTest() {
		assertEquals(0, ret.getColor());
	}

	@Test(expected=NoSuchElementException.class)
	public void acceptNoCallTest() {
		ret.accept(machine);
	}

	@Test
	public void acceptTest() {
		machine.setInstrPointer(10);
		machine.saveReturnAddress();
		machine.setInstrPointer(0);
		ret.accept(machine);
		assertEquals(10, machine.getInstrPointer());
	}
}
