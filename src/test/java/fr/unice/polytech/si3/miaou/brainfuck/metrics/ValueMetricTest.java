package fr.unice.polytech.si3.miaou.brainfuck.metrics;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValueMetricTest {
	ValueMetric vm;

	@Before
	public void setUp() {
		vm = new ValueMetric("test") {};
	}

	@Test
	public void test() {
		assertEquals("test: 0", vm.toString());
		vm.set(10);
		assertEquals("test: 10", vm.toString());
	}
}
