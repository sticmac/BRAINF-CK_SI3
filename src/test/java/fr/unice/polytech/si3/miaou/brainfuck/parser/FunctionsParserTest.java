package fr.unice.polytech.si3.miaou.brainfuck.parser;

import fr.unice.polytech.si3.miaou.brainfuck.InstructionSet;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import fr.unice.polytech.si3.miaou.brainfuck.exceptions.SyntaxFunctionException;
import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.JumpTable;

public class FunctionsParserTest {
	private FunctionsParser parser;
	private Stream<String> lines;
	private InstructionSet instructionSet;

	@Before
	public void defineContext() {
		instructionSet = new InstructionSet();
		parser = new FunctionsParser(instructionSet);
	}

	@Ignore
	@Test
	public void testExists() {
		List<String> linesBuilder = new LinkedList<>();
		linesBuilder.add("FUNC Miaou");
		linesBuilder.add("++--");
		linesBuilder.add("RET");
		lines = linesBuilder.stream();
		lines.map(parser);
		assertNotNull(instructionSet.getProc("Miaou"));
	}

	@Test
	public void declareTest() {
		parser.apply("FUNC MIAOU");
		parser.apply("RET");
		assertEquals("MIAOU", instructionSet.getProc("MIAOU").getName());
		assertEquals(1, parser.getCounter());
	}

	@Test
	public void declareShortSyntaxTest() {
		parser.apply("FUNC MIAOU");
		parser.apply("+++");
		parser.apply("+++");
		parser.apply("RET");
		parser.apply("FUNC NYAN");
		parser.apply("MIAOU");
		parser.apply("RET");
		parser.apply("NYAN");

		assertEquals(9, parser.getCounter());

		assertEquals("MIAOU", instructionSet.getProc("MIAOU").getName());

		Machine machine = new Machine(10, new JumpTable());
		machine.executeOp(instructionSet.getProc("NYAN"));
		assertEquals(7, machine.getInstrPointer());
		machine.executeOp(instructionSet.getProc("MIAOU"));
		assertEquals(0, machine.getInstrPointer());
	}

	@Test(expected=SyntaxFunctionException.class)
	public void retSyntaxErrorTest() {
		parser.apply("RET");
	}

	@Test(expected=SyntaxFunctionException.class)
	public void doubledeclareFuncErrorTest() {
		parser.apply("FUNC MIAOU");
		parser.apply("FUNC NYAN");
	}

	@Test(expected=SyntaxFunctionException.class)
	public void noNameTest() {
		parser.apply("FUNC");
	}
}
