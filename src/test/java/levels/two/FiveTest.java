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


public class FiveTest {
	public static final int CELL_COUNT = 30000;

	@Before
	public void setUpTest() {
		ComplianceSuite.reset();
	}

	@Test
	public void incrementC0By7Short() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("incrementC0by7Short.bf");
		ComplianceSuite.writeToFile(path, "+++++++");

		try {
			Main.main(new String[] { "--nometrics", "-p", path });
		} catch (SecurityException se) {
		}

		assertEquals("C0: 7\n", ComplianceSuite.outContent.toString());
		assertEquals(0, ComplianceSuite.errContent.size());
		assertEquals(0, ComplianceSuite.exitCode);
	}

	@Test
	public void variousShortInstr() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("shorttests.bf");
		ComplianceSuite.writeToFile(path, "+++>>>++<+<<--");

		try {
			Main.main(new String[] { "--nometrics", "-p", path });
		} catch (SecurityException se) {
		}

		assertEquals("C0: 1\nC2: 1\nC3: 2\n", ComplianceSuite.outContent.toString());
		assertEquals(0, ComplianceSuite.errContent.size());
		assertEquals(0, ComplianceSuite.exitCode);
	}

	@Test
	public void mixedSyntax() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("mixedsyntaxtests.bf");
		ComplianceSuite.writeToFile(path, "++\nINCR\nRIGHT\n>>++\nLEFT\n+<<-\nDECR");

		try {
			Main.main(new String[] { "--nometrics", "-p", path });
		} catch (SecurityException se) {
		}

		assertEquals("C0: 1\nC2: 1\nC3: 2\n", ComplianceSuite.outContent.toString());
		assertEquals(0, ComplianceSuite.errContent.size());
		assertEquals(0, ComplianceSuite.exitCode);
	}

	@Test
	public void syntaxErrorShort() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("syntaxerrorshort.bf");
		ComplianceSuite.writeToFile(path, "+++<<$$+");

		try {
			Main.main(new String[] { "-p", path });
		} catch (SecurityException se) {
		}

		assertEquals(0, ComplianceSuite.outContent.size());
		assertEquals(true, ComplianceSuite.errContent.toString().contains("$"));
		assertEquals(6, ComplianceSuite.exitCode);
	}

	@Test
	public void syntaxErrorMixed() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("syntaxerrormixed.bf");
		ComplianceSuite.writeToFile(path, "++\nINCR\n<<\nXNCR\nINCR");

		try {
			Main.main(new String[] { "-p", path });
		} catch (SecurityException se) {
		}

		assertEquals(0, ComplianceSuite.outContent.size());
		assertEquals(true, ComplianceSuite.errContent.toString().contains("X"));
		assertEquals(6, ComplianceSuite.exitCode);
	}
}
