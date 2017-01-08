package fr.unice.polytech.si3.miaou.brainfuck;

import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.util.EnumSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.miaou.brainfuck.exceptions.ArgumentsException;

public class ModeTest {
	@Test
	public void modeTest() {
		Mode[] m = Mode.values();
		List<String> mn = Arrays.stream(m).map(Mode::name).collect(Collectors.toList());
		assertEquals(5, mn.size());
		assertTrue(mn.contains("RUN"));
		assertTrue(mn.contains("REWRITE"));
		assertTrue(mn.contains("TRANSLATE"));
		assertTrue(mn.contains("CHECK"));
		assertTrue(mn.contains("TRACE"));
	}

	@Test
	public void ofTest() {
		assertEquals(Mode.RUN, Mode.of("RUN").get());
		assertEquals(Mode.REWRITE, Mode.of("REWRITE").get());
		assertEquals(Mode.TRANSLATE, Mode.of("TRANSLATE").get());
		assertEquals(Mode.CHECK, Mode.of("CHECK").get());
		assertEquals(Mode.TRACE, Mode.of("TRACE").get());
	}

	@Test
	public void ofNotFoundTest() {
		assertFalse(Mode.of("TESTNONEXISTENTMODE").isPresent());
	}

	@Test
	public void addToTest() {
		EnumSet<Mode> es = EnumSet.noneOf(Mode.class);
		Mode.addTo(es, Mode.RUN);
		assertEquals(1, es.size());
		assertTrue(es.contains(Mode.RUN));
	}

	private void testConflicts(Mode mode1, Mode mode2) {
		EnumSet<Mode> es = EnumSet.of(mode1);
		Mode.addTo(es, mode2);

	}

	@Test
	public void addToConflictsRunTest() {
		EnumSet<Mode> es = EnumSet.of(Mode.RUN);
		Mode.addTo(es, Mode.TRANSLATE);
		assertEquals(1, es.size());
		assertTrue(es.contains(Mode.TRANSLATE));
	}

	@Test
	public void addToConflictsRun2Test() {
		EnumSet<Mode> es = EnumSet.of(Mode.RUN);
		Mode.addTo(es, Mode.REWRITE);
		assertEquals(1, es.size());
		assertTrue(es.contains(Mode.REWRITE));
	}

	@Test(expected=ArgumentsException.class)
	public void addToConflicts2Test() {
		testConflicts(Mode.REWRITE, Mode.TRANSLATE);
	}

	@Test(expected=ArgumentsException.class)
	public void addToConflicts3Test() {
		testConflicts(Mode.TRANSLATE, Mode.RUN);
	}

	@Test(expected=ArgumentsException.class)
	public void addToConflicts4Test() {
		testConflicts(Mode.TRANSLATE, Mode.REWRITE);
	}

	@Test(expected=ArgumentsException.class)
	public void addToConflicts5Test() {
		testConflicts(Mode.REWRITE, Mode.RUN);
	}

	@Test
	public void addToConflictsRun3Test() {
		EnumSet<Mode> es = EnumSet.of(Mode.RUN);
		Mode.addTo(es, Mode.RUN);
		assertEquals(1, es.size());
		assertTrue(es.contains(Mode.RUN));
	}

	@Test(expected=ArgumentsException.class)
	public void addToConflicts8Test() {
		testConflicts(Mode.TRANSLATE, Mode.TRANSLATE);
	}

	@Test(expected=ArgumentsException.class)
	public void addToConflicts9Test() {
		testConflicts(Mode.REWRITE, Mode.REWRITE);
	}

	@Test
	public void addToNoConflict1Test() {
		EnumSet<Mode> es = EnumSet.of(Mode.CHECK);
		Mode.addTo(es, Mode.RUN);
		assertEquals(2, es.size());
		assertTrue(es.contains(Mode.CHECK));
		assertTrue(es.contains(Mode.RUN));
	}

	@Test
	public void addToNoConflict2Test() {
		EnumSet<Mode> es = EnumSet.of(Mode.RUN);
		Mode.addTo(es, Mode.TRACE);
		assertEquals(2, es.size());
		assertTrue(es.contains(Mode.RUN));
		assertTrue(es.contains(Mode.TRACE));
	}
}
