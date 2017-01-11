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


public class TenTest {
	public static final int CELL_COUNT = 30000;

	@Before
	public void setUpTest() {
		ComplianceSuite.reset();
	}

	@Test
	public void noBracket() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("noBracket.bf");
		ComplianceSuite.writeToFile(path, "++>+>+-<--+-");

		try {
			Main.main(new String[] { "-p", path, "--check" });
		} catch (SecurityException se) {
		}

		assertEquals(0, ComplianceSuite.outContent.size());
		assertEquals(0, ComplianceSuite.errContent.size());
		assertEquals(0, ComplianceSuite.exitCode);
	}

	@Test
	public void bracketMatching() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("bracketMatching.bf");
		ComplianceSuite.writeToFile(path, "++[>+>+\nBACK\n-<-\nJUMP\n[-\nBACK\n+-]");

		try {
			Main.main(new String[] { "-p", path, "--check" });
		} catch (SecurityException se) {
		}

		assertEquals(0, ComplianceSuite.outContent.size());
		assertEquals(0, ComplianceSuite.errContent.size());
		assertEquals(0, ComplianceSuite.exitCode);
	}

	@Test
	public void bracketMismatchLeft() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("bracketMismatchLeft.bf");
		ComplianceSuite.writeToFile(path, "++[>+>+]-<-[[-+-]");

		try {
			Main.main(new String[] { "-p", path, "--check" });
		} catch (SecurityException se) {
		}

		assertEquals(0, ComplianceSuite.outContent.size());
		assertEquals(true, ComplianceSuite.errContent.toString().contains("BracketMismatchException"));
		assertEquals(4, ComplianceSuite.exitCode);
	}

	@Test
	public void bracketMismatchRight() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("bracketMismatchRight.bf");
		ComplianceSuite.writeToFile(path, "++[>+>+]-<-[-]+-]");

		try {
			Main.main(new String[] { "-p", path, "--check" });
		} catch (SecurityException se) {
		}

		assertEquals(0, ComplianceSuite.outContent.size());
		assertEquals(true, ComplianceSuite.errContent.toString().contains("BracketMismatchException"));
		assertEquals(4, ComplianceSuite.exitCode);
	}
}
