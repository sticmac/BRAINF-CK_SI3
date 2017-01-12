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


public class EightTest {
	public static final int CELL_COUNT = 30000;

	@Before
	public void setUpTest() {
		ComplianceSuite.reset();
	}

	@Test
	public void longToImageAllInstr() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("longtoimageallinstr.bf");
		ComplianceSuite.writeToFile(path, "INCR\nDECR\nLEFT\nRIGHT\nJUMP\nBACK\nIN\nOUT");

		try {
			Main.main(new String[] { "-p", path, "--translate" });
		} catch (SecurityException se) {
		}

		int[] colors = ComplianceSuite.readStdOutImage();

		assertArrayEquals(new int[] {0xFFFFFFFF, 0xFF4B0082, 0xFF9400D3, 0xFF0000FF, 0xFFFF7F00, 0xFFFF0000, 0xFF00FF00, 0xFFFFFF00, 0xFF000000}, colors);
		assertEquals(0, ComplianceSuite.errContent.size());
		assertEquals(0, ComplianceSuite.exitCode);
	}

	@Test
	public void shortToImageAllInstr() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("shorttoimageallinstr.bf");
		ComplianceSuite.writeToFile(path, "+-<>[],.");

		try {
			Main.main(new String[] { "-p", path, "--translate" });
		} catch (SecurityException se) {
		}

		int[] colors = ComplianceSuite.readStdOutImage();

		assertArrayEquals(new int[] {0xFFFFFFFF, 0xFF4B0082, 0xFF9400D3, 0xFF0000FF, 0xFFFF7F00, 0xFFFF0000, 0xFF00FF00, 0xFFFFFF00, 0xFF000000}, colors);
		assertEquals(0, ComplianceSuite.errContent.size());
		assertEquals(0, ComplianceSuite.exitCode);
	}

	@Test
	public void mixedToImageAllInstr() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("mixedtoimageallinstr.bf");
		ComplianceSuite.writeToFile(path, "+\nINCR\n-\nDECR\n<\nLEFT\n>\nRIGHT\n[\nJUMP\n]\nBACK\n,\nIN\n.\nOUT");

		try {
			Main.main(new String[] { "-p", path, "--translate" });
		} catch (SecurityException se) {
		}

		int[] colors = ComplianceSuite.readStdOutImage();

		assertArrayEquals(new int[] {0xFFFFFFFF, 0xFFFFFFFF, 0xFF4B0082, 0xFF4B0082, 0xFF9400D3, 0xFF9400D3, 0xFF0000FF, 0xFF0000FF, 0xFFFF7F00, 0xFFFF7F00, 0xFFFF0000, 0xFFFF0000, 0xFF00FF00, 0xFF00FF00, 0xFFFFFF00, 0xFFFFFF00}, colors);
		assertEquals(0, ComplianceSuite.errContent.size());
		assertEquals(0, ComplianceSuite.exitCode);
	}

	@Test
	public void invalidShortInstrTranslate() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("invalidshortinstrtranslate.bf");
		ComplianceSuite.writeToFile(path, "++$$");

		try {
			Main.main(new String[] { "-p", path, "--translate" });
		} catch (SecurityException se) {
		}

		assertEquals(0, ComplianceSuite.outContent.size());
		assertEquals(true, ComplianceSuite.errContent.toString().contains("$"));
		assertEquals(6, ComplianceSuite.exitCode);
	}

	@Test
	public void invalidMixedInstrTranslate() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("invalidmixedinstrtranslate.bf");
		ComplianceSuite.writeToFile(path, "++\nINCR\n<<\nXNCR\nINCR");

		try {
			Main.main(new String[] { "-p", path, "--translate" });
		} catch (SecurityException se) {
		}

		assertEquals(0, ComplianceSuite.outContent.size());
		assertEquals(true, ComplianceSuite.errContent.toString().contains("X"));
		assertEquals(6, ComplianceSuite.exitCode);
	}

	@Test
	public void incrementC0By7ImageToImage() throws IOException, FileNotFoundException {
		String path = ComplianceSuite.createTmpFile("incrementC0by7Imagetoimage.bmp");
		ComplianceSuite.writeImage(path, new int[] { 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF}, 1);

		try {
			Main.main(new String[] { "-p", path, "--translate" });
		} catch (SecurityException se) {
		}

		int[] colors = ComplianceSuite.readStdOutImage();

		assertArrayEquals(new int[] {0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF}, colors);
		assertEquals(0, ComplianceSuite.errContent.size());
		assertEquals(0, ComplianceSuite.exitCode);
	}
}
