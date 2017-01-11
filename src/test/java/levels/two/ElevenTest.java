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


public class ElevenTest {
	public static final int CELL_COUNT = 30000;

	@Before
	public void setUpTest() {
		ComplianceSuite.reset();
	}

	@Test
	public void decrementLoop() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("decrementLoop.bf");
		ComplianceSuite.writeToFile(path, "[-]");

		try {
			Main.main(new String[] { "--nometrics", "-p", path });
		} catch (SecurityException se) {
		}

		assertEquals(0, ComplianceSuite.outContent.size());
		assertEquals(0, ComplianceSuite.errContent.size());
		assertEquals(0, ComplianceSuite.exitCode);
	}

	@Test
	public void loopIncr() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("loopIncr.bf");
		ComplianceSuite.writeToFile(path, "++[>++[>+>++<<-]<-]");

		try {
			Main.main(new String[] { "--nometrics", "-p", path });
		} catch (SecurityException se) {
		}

		assertEquals("C2: 4\nC3: 8\n", ComplianceSuite.outContent.toString());
		assertEquals(0, ComplianceSuite.errContent.size());
		assertEquals(0, ComplianceSuite.exitCode);
	}

	@Test
	public void conditionalJumpFalse() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("conditionalJumpFalse.bf");
		ComplianceSuite.writeToFile(path, "+++>++--[>++<-]");

		try {
			Main.main(new String[] { "--nometrics", "-p", path });
		} catch (SecurityException se) {
		}

		assertEquals("C0: 3\n", ComplianceSuite.outContent.toString());
		assertEquals(0, ComplianceSuite.errContent.size());
		assertEquals(0, ComplianceSuite.exitCode);
	}

	@Test
	public void bracketMismatchLeftExec() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("bracketMismatchLeftExec.bf");
		ComplianceSuite.writeToFile(path, "++[>+>+]-<-[[-+-]");

		try {
			Main.main(new String[] { "-p", path });
		} catch (SecurityException se) {
		}

		assertEquals(0, ComplianceSuite.outContent.size());
		assertEquals(true, ComplianceSuite.errContent.toString().contains("BracketMismatchException"));
		assertEquals(4, ComplianceSuite.exitCode);
	}
}
