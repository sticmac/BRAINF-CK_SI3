package fr.unice.polytech.si3.miaou.brainfuck;

import java.util.*;
import java.io.*;
import java.nio.file.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;

import org.junit.rules.TemporaryFolder;


import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Jump;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Back;
import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.BracketMismatchException;

public class JumpTableTest {
	JumpTable jt;

	@Before
	public void setUp() throws IOException {
		jt = new JumpTable();
	}

	@Test
	public void checkTest() {
		jt.check();
		assertTrue(true);
	}

	@Test(expected=BracketMismatchException.class)
	public void checkFailTest() {
		jt.bind(new Jump(), 0);
		jt.check();
	}

	@Test(expected=BracketMismatchException.class)
	public void bindFailTest() {
		jt.bind(new Back(), 0);
	}
}
