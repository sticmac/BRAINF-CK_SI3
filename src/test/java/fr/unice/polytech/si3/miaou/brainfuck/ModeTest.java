package fr.unice.polytech.si3.miaou.brainfuck;

import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;

import org.junit.rules.TemporaryFolder;

public class ModeTest {
	@Test
	public void modeTest() {
		Mode[] m = Mode.values();
		List<String> mn = Arrays.stream(m).map(Mode::name).collect(Collectors.toList());
		assertEquals(4, mn.size());
		assertTrue(mn.contains("RUN"));
		assertTrue(mn.contains("REWRITE"));
		assertTrue(mn.contains("TRANSLATE"));
		assertTrue(mn.contains("CHECK"));
	}
}
