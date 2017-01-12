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


public class ThreeTest {
	public static final int CELL_COUNT = 30000;

	@Before
	public void setUpTest() {
		ComplianceSuite.reset();
	}

	@Test
	public void incrementC2By1() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("incrementC2by1.bf");
		ComplianceSuite.writeToFile(path, "RIGHT\nRIGHT\nINCR\n");

		try {
			Main.main(new String[] { "--nometrics", "-p", path });
		} catch (SecurityException se) {
		}

		assertEquals("C2: 1\n", ComplianceSuite.outContent.toString());
		assertEquals(0, ComplianceSuite.errContent.size());
		assertEquals(0, ComplianceSuite.exitCode);
	}

	@Test
	public void rightOOM() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("rightOOM.bf");
		ComplianceSuite.writeToFile(path, String.join("", Collections.nCopies(CELL_COUNT, "RIGHT\n")));

		try {
			Main.main(new String[] { "-p", path });
		} catch (SecurityException se) {
		}

		assertEquals(0, ComplianceSuite.outContent.size());
		assertEquals(true, ComplianceSuite.errContent.toString().contains(CELL_COUNT + ""));
		assertEquals(2, ComplianceSuite.exitCode);
	}
}
