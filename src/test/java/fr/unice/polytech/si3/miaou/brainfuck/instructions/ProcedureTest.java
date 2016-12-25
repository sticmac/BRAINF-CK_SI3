package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import fr.unice.polytech.si3.miaou.brainfuck.exceptions.FunctionUsageException;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.SyntaxFunctionException;
import org.junit.Before;
import org.junit.Test;

public class ProcedureTest {
	private Procedure procedure;

	@Before
	@Test
	public void defineContext() {
		procedure = new Procedure("miaou", 0, "a", "b");
	}

	@Test(expected = FunctionUsageException.class)
	public void testTooMuchParameters() {
		procedure.setParametersValues(new int[]{1,2,3});
	}

	@Test(expected = FunctionUsageException.class)
	public void testTooFewParameters() {
		procedure.setParametersValues(new int[]{1});
	}
}
