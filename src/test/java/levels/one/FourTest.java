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


public class FourTest {
	public static final int CELL_COUNT = 30000;

	@Before
	public void setUpTest() {
		ComplianceSuite.reset();
	}

	@Test
	public void incrementC1By1WithLeft() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("incrementC1by1withleft.bf");
		ComplianceSuite.writeToFile(path, "RIGHT\nRIGHT\nLEFT\nINCR\n");

		try {
			Main.main(new String[] {"--nometrics", "-p", path });
		} catch (SecurityException se) {
		}

		assertEquals("C1: 1\n", ComplianceSuite.outContent.toString());
		assertEquals(0, ComplianceSuite.errContent.size());
		assertEquals(0, ComplianceSuite.exitCode);
	}

	@Test
	public void rightLeftMax() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("rightleftmax.bf");
		ComplianceSuite.writeToFile(path, String.join("", Collections.nCopies(CELL_COUNT - 1, "RIGHT\n")) + String.join("", Collections.nCopies(CELL_COUNT - 1, "LEFT\n")));

		try {
			Main.main(new String[] { "--nometrics", "-p", path });
		} catch (SecurityException se) {
		}

		assertEquals(0, ComplianceSuite.outContent.size());
		assertEquals(0, ComplianceSuite.errContent.size());
		assertEquals(0, ComplianceSuite.exitCode);
	}

	@Test
	public void leftOOM() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("leftOOM.bf");
		ComplianceSuite.writeToFile(path, "LEFT\n");

		try {
			Main.main(new String[] { "-p", path });
		} catch (SecurityException se) {
		}

		assertEquals(0, ComplianceSuite.outContent.size());
		assertEquals(true, ComplianceSuite.errContent.toString().contains("-1"));
		assertEquals(2, ComplianceSuite.exitCode);
	}
}
