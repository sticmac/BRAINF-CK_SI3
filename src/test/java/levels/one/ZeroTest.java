package test.levels.one;

import test.levels.ComplianceSuite;

import fr.unice.polytech.si3.miaou.brainfuck.Main;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.Class;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class ZeroTest {
	@Test
	public void emptyProgram() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("empty.bf");
		ComplianceSuite.writeToFile(path, "");

		try {
			Main.main(new String[] { "--nometrics", "-p", path });
		} catch (SecurityException se) {
		}

		assertEquals(0, ComplianceSuite.outContent.size());
		assertEquals(0, ComplianceSuite.errContent.size());
		System.err.println(ComplianceSuite.errContent.toString());
		assertEquals(0, ComplianceSuite.exitCode);
	}

	@Test
	public void invalidInstr() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("invalidinstr.bf");
		ComplianceSuite.writeToFile(path, "XNCR");

		try {
			Main.main(new String[] { "--nometrics", "-p", path });
		} catch (SecurityException se) {
		}

		assertEquals(0, ComplianceSuite.outContent.size());
		assertEquals(true, ComplianceSuite.errContent.toString().contains("X"));
		assertEquals(6, ComplianceSuite.exitCode);
	}

	@Test
	public void noFilename() throws IOException, FileNotFoundException {
		try {
			Main.main(new String[] { "-p" });
		} catch (SecurityException se) {
		}

		assertEquals(0, ComplianceSuite.outContent.size());
		assertEquals(true, ComplianceSuite.errContent.toString().contains("No value"));
		assertEquals(5, ComplianceSuite.exitCode);
	}

	@Test(expected = FileNotFoundException.class)
	public void fileNotFound() throws IOException, FileNotFoundException {
		try {
			Main.main(new String[] { "-p", "nonexistentfile.bf"});
		} catch (SecurityException se) {
		}
	}
}
