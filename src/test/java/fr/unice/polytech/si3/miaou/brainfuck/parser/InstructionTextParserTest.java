package fr.unice.polytech.si3.miaou.brainfuck.parser;

import fr.unice.polytech.si3.miaou.brainfuck.InstructionSet;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.IntStream;

import fr.unice.polytech.si3.miaou.brainfuck.exceptions.InvalidInstructionException;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Incr;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Procedure;

public class InstructionTextParserTest {
	private InstructionTextParser parser;
	private InstructionParser ip;
	private InstructionSet is;
	private Procedure proc;

	@Before
	public void setUp() {
		proc = new Procedure("testProc", 0);
		is = new InstructionSet();
		is.addProc(proc);
		ip = new InstructionParser(Stream.empty());
		parser = new InstructionTextParser(ip, is);
	}

	@Test
	public void doNothingTest() {
		parser.accept("");
		assertTrue(ip.get().isEmpty());
	}

	@Test(expected=InvalidInstructionException.class)
	public void invalidInstructionTest() {
		parser.accept("test");
	}

	@Test
	public void add2LongIncrTest() {
		parser.accept("INCR");
		parser.accept("INCR");
		assertEquals(2, ip.get().size());
		assertEquals(Incr.class, ip.get().get(0).getClass());
		assertEquals(Incr.class, ip.get().get(1).getClass());
	}

	@Test
	public void add2ShortIncrWithBlankTest() {
		parser.accept("+ \t+");
		assertEquals(2, ip.get().size());
		assertEquals(Incr.class, ip.get().get(0).getClass());
		assertEquals(Incr.class, ip.get().get(1).getClass());
	}

	@Test
	public void addProcCallTest() {
		parser.accept("testProc");
		assertEquals(1, ip.get().size());
		assertEquals(proc, ip.get().get(0));
	}
}
