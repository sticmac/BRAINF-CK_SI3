package fr.unice.polytech.si3.miaou.brainfuck;

import java.util.List;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;

import org.junit.rules.TemporaryFolder;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;


public class TranslatorTest {
	ByteArrayOutputStream outStream;

	Translator t;
	Instruction instr;
	List<Instruction> l = new ArrayList<>();

	class Instr extends Instruction {
		Instr(String s, char c, int i) { super(s, c, i); }
		@Override public void accept(Machine m) {}
	}

	@Before
	public void setUp() {
		outStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outStream));

		t = new Translator();
		instr = new Instr("test", 't', 0xFFFFFFFF);
		l.add(instr);
		l.add(new Instr("test2", 'c', 0x00000000));
	}

	@Test
	public void toLongSyntaxTest() {
		t.toLongSyntax(instr);
		assertEquals("test\n", outStream.toString());
	}

	@Test
	public void toLongSyntaxListTest() {
		t.toLongSyntax(l);
		assertEquals("test\ntest2\n", outStream.toString());
	}

	@Test
	public void toShortSyntaxTest() {
		t.toShortSyntax(instr);
		assertEquals("t", outStream.toString());
	}

	@Test
	public void toShortSyntaxListTest() {
		t.toShortSyntax(l);
		assertEquals("tc", outStream.toString());
	}

	@Test
	public void toColorTest() {
		assertEquals(0xFFFFFFFF, t.toColor(instr));
	}

	@Test
	public void toColorListTest() {
		List<Integer> l2 = t.toColor(l);
		assertEquals(2, l2.size());
		assertEquals(0xFFFFFFFF, l2.get(0).intValue());
		assertEquals(0x00000000, l2.get(1).intValue());
	}
}
