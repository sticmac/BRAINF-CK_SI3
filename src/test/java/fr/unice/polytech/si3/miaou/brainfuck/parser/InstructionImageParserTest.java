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

public class InstructionImageParserTest {
	private InstructionImageParser parser;
	private InstructionParser ip;

	@Before
	public void setUp() {
		ip = new InstructionParser(IntStream.empty());
		parser = new InstructionImageParser(ip, new InstructionSet());
	}

	@Test
	public void doNothingTest() {
		parser.accept(0xFF000000);
		assertTrue(ip.get().isEmpty());
	}

	@Test(expected=InvalidInstructionException.class)
	public void invalidInstructionTest() {
		parser.accept(0xDD0000DD);
	}

	@Test
	public void add2IncrTest() {
		parser.accept(0xFFFFFFFF);
		parser.accept(0xFFFFFFFF);
		assertEquals(2, ip.get().size());
		assertEquals(Incr.class, ip.get().get(0).getClass());
		assertEquals(Incr.class, ip.get().get(1).getClass());
	}
}
