package fr.unice.polytech.si3.miaou.brainfuck;

import java.util.*;
import java.io.*;
import java.nio.file.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;

import org.junit.rules.TemporaryFolder;


import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;
import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;


public class LoggerTest {
	Logger log;
	String filename;
	File file;
	Instruction instr;
	Machine machine;

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	@Before
	public void setUp() throws IOException {
		instr = new Instruction("test", '\0', 0) {
			@Override public void accept(Machine machine) {}
		};

		filename = testFolder.newFile("test.txt").getPath();
		log = new Logger(filename);
		filename = filename.substring(0, filename.length() - 3) + "log";
		machine = new Machine(0, new JumpTable());
	}

	@Test
	public void logStepTest() throws IOException {
		assertTrue((new String(Files.readAllBytes(Paths.get(filename)))).isEmpty());
		machine.writeMemory((byte)4);
		machine.setLocation(3);
		machine.writeMemory((byte)2);
		log.logStep(5, instr, machine);
		String str = new String(Files.readAllBytes(Paths.get(filename)));
		assertTrue(str.contains("test"));
		assertTrue(str.contains("1"));
		assertTrue(str.contains("130"));
		assertTrue(str.contains("C0"));
		assertTrue(str.contains("132"));
		assertTrue(str.contains("C3"));
		assertTrue(str.contains("5"));
	}
}
