package test.levels.one;

import test.levels.ComplianceSuite;

import fr.unice.polytech.si3.miaou.brainfuck.Main;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Collections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.Class;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.Before;


public class TwoTest {
	public static final int MAX_CELL_VAL = Byte.MAX_VALUE - Byte.MIN_VALUE;

	@Before
	public void setUpTest() {
		ComplianceSuite.reset();
	}

	@Test
	public void incrementC0By7DecrementC0By2() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("incrementC0by7DecrementC0by2.bf");
		ComplianceSuite.writeToFile(path, "INCR\nINCR\nINCR\nINCR\nINCR\nINCR\nINCR\nDECR\nDECR");

		try {
			Main.main(new String[] { "--nometrics", "-p", path });
		} catch (SecurityException se) {
		}

		assertEquals("C0: 5\n", ComplianceSuite.outContent.toString());
		assertEquals(0, ComplianceSuite.errContent.size());
		assertEquals(0, ComplianceSuite.exitCode);
	}

	@Test
	public void incrementDecrementMax() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("incrementDecrementC0byMAX_CELL_VAL.bf");
		ComplianceSuite.writeToFile(path, String.join("", Collections.nCopies(MAX_CELL_VAL, "INCR\n")) + String.join("", Collections.nCopies(MAX_CELL_VAL, "DECR\n")));

		try {
			Main.main(new String[] { "--nometrics", "-p", path });
		} catch (SecurityException se) {
		}

		assertEquals("", ComplianceSuite.outContent.toString());
		assertEquals(0, ComplianceSuite.errContent.size());
		assertEquals(0, ComplianceSuite.exitCode);
	}

	@Test
	public void decrementOverflow() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("decrementC0Overflow.bf");
		ComplianceSuite.writeToFile(path, "DECR");

		try {
			Main.main(new String[] { "-p", path });
		} catch (SecurityException se) {
		}

		assertEquals(0, ComplianceSuite.outContent.size());
		assertEquals(true, ComplianceSuite.errContent.toString().contains("Below"));
		assertEquals(1, ComplianceSuite.exitCode);
	}

}
