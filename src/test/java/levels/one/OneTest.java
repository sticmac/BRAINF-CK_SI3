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


public class OneTest {
	public static final int MAX_CELL_VAL = Byte.MAX_VALUE - Byte.MIN_VALUE;

	@Before
	public void setUpTest() {
		ComplianceSuite.reset();
	}

	@Test
	public void incrementC0By7() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("incrementC0by7.bf");
		ComplianceSuite.writeToFile(path, "INCR\nINCR\nINCR\nINCR\nINCR\nINCR\nINCR");

		try {
			Main.main(new String[] { "--nometrics", "-p", path });
		} catch (SecurityException se) {
		}

		assertEquals("C0: 7\n", ComplianceSuite.outContent.toString());
		assertEquals(0, ComplianceSuite.errContent.size());
		assertEquals(0, ComplianceSuite.exitCode);
	}

	@Test
	public void incrementMax() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("incrementC0byMAX_CELL_VAL.bf");
		ComplianceSuite.writeToFile(path, String.join("", Collections.nCopies(MAX_CELL_VAL, "INCR\n")));

		try {
			Main.main(new String[] {"--nometrics",  "-p", path });
		} catch (SecurityException se) {
		}

		assertEquals("C0: " + MAX_CELL_VAL + "\n", ComplianceSuite.outContent.toString());
		assertEquals(0, ComplianceSuite.errContent.size());
		assertEquals(0, ComplianceSuite.exitCode);
	}

	@Test
	public void incrementOverflow() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("incrementC0Overflow.bf");
		ComplianceSuite.writeToFile(path, String.join("", Collections.nCopies(MAX_CELL_VAL+1, "INCR\n")));

		try {
			Main.main(new String[] { "-p", path });
		} catch (SecurityException se) {
		}

		assertEquals(0, ComplianceSuite.outContent.size());
		assertEquals(true, ComplianceSuite.errContent.toString().contains("Above"));
		assertEquals(1, ComplianceSuite.exitCode);
	}

}
