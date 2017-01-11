package fr.unice.polytech.si3.miaou.brainfuck.exceptions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SyntaxFunctionExceptionTest {
	BrainfuckException bfexception;

	@Before
	public void setUp() {
		bfexception = new SyntaxFunctionException("test");
	}

	@Test
	public void getErrorCodeTest() {
		assertEquals(11, bfexception.getErrorCode());
	}

	@Test
	public void getMessageTest() {
		assertEquals("test", bfexception.getMessage());
	}
}
