package fr.unice.polytech.si3.miaou.brainfuck.parser;

import fr.unice.polytech.si3.miaou.brainfuck.InstructionSet;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class CommentsAndIndentationParserTest {
	private CommentsAndIndentationParser parser;

	@Before
	public void setUp() {
		parser = new CommentsAndIndentationParser();
	}

	@Test
	public void removeWhitespacesTest() {
		assertEquals("te st", parser.apply(" \tte st \t"));
	}

	@Test
	public void removeCommentTest() {
		assertEquals("test", parser.apply("test#test"));
	}

	@Test
	public void removeCommentWhitespaceTest() {
		assertEquals("test", parser.apply(" test # test "));
	}

	@Test
	public void doNothingTest() {
		assertEquals("test test", parser.apply("test test"));
	}
}
