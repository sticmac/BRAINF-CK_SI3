package fr.unice.polytech.si3.miaou.brainfuck.metrics;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MetricTest {
	Metric m;

	@Before
	public void setUp() {
		m = new Metric("test") {};
	}

	@Test
	public void getNameTest() {
		assertEquals("test", m.getName());
	}

	@Test
	public void toStringTest() {
		assertEquals("test: 2", m.toString(2));
		assertEquals("test: test", m.toString("test"));
	}
}
