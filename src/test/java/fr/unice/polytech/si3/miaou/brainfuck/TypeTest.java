package fr.unice.polytech.si3.miaou.brainfuck;

import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;

import org.junit.rules.TemporaryFolder;

public class TypeTest {
	@Test
	public void modeTest() {
		Type[] t = Type.values();
		List<String> tn = Arrays.stream(t).map(Type::name).collect(Collectors.toList());
		assertEquals(2, tn.size());
		assertTrue(tn.contains("TEXT"));
		assertTrue(tn.contains("IMAGE"));
	}
}
