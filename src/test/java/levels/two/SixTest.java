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


public class SixTest {
	public static final int CELL_COUNT = 30000;

	@Before
	public void setUpTest() {
		ComplianceSuite.reset();
	}

	@Test
	public void longToShortAllInstr() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("longtoshortallinstr.bf");
		ComplianceSuite.writeToFile(path, "INCR\nDECR\nLEFT\nRIGHT\nJUMP\nBACK\nIN\nOUT");

		try {
			Main.main(new String[] { "-p", path, "--rewrite" });
		} catch (SecurityException se) {
		}

		assertEquals("+-<>[],.", ComplianceSuite.outContent.toString());
		assertEquals(0, ComplianceSuite.errContent.size());
		assertEquals(0, ComplianceSuite.exitCode);
	}

	@Test
	public void shortToShortAllInstr() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("shorttoshortallinstr.bf");
		ComplianceSuite.writeToFile(path, "+-<>[],.");

		try {
			Main.main(new String[] { "-p", path, "--rewrite" });
		} catch (SecurityException se) {
		}

		assertEquals("+-<>[],.", ComplianceSuite.outContent.toString());
		assertEquals(0, ComplianceSuite.errContent.size());
		assertEquals(0, ComplianceSuite.exitCode);
	}

	@Test
	public void mixedToShortAllInstr() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("mixedtoshortallinstr.bf");
		ComplianceSuite.writeToFile(path, "+\nINCR\n-\nDECR\n<\nLEFT\n>\nRIGHT\n[\nJUMP\n]\nBACK\n,\nIN\n.\nOUT");

		try {
			Main.main(new String[] { "-p", path, "--rewrite" });
		} catch (SecurityException se) {
		}

		assertEquals("++--<<>>[[]],,..", ComplianceSuite.outContent.toString());
		assertEquals(0, ComplianceSuite.errContent.size());
		assertEquals(0, ComplianceSuite.exitCode);
	}

	@Test
	public void invalidShortInstrRewrite() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("invalidshortinstrrewrite.bf");
		ComplianceSuite.writeToFile(path, "++$$");

		try {
			Main.main(new String[] { "-p", path, "--rewrite" });
		} catch (SecurityException se) {
		}

		assertEquals(0, ComplianceSuite.outContent.size());
		assertEquals(true, ComplianceSuite.errContent.toString().contains("$"));
		assertEquals(6, ComplianceSuite.exitCode);
	}

	@Test
	public void invalidMixedInstrRewrite() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("invalidmixedinstrrewrite.bf");
		ComplianceSuite.writeToFile(path, "++\nINCR\n<<\nXNCR\nINCR");

		try {
			Main.main(new String[] { "-p", path, "--rewrite" });
		} catch (SecurityException se) {
		}

		assertEquals(0, ComplianceSuite.outContent.size());
		assertEquals(true, ComplianceSuite.errContent.toString().contains("X"));
		assertEquals(6, ComplianceSuite.exitCode);
	}
}
