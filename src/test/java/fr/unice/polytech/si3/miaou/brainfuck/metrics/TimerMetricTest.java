package fr.unice.polytech.si3.miaou.brainfuck.metrics;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TimerMetricTest {
	TimerMetric tm;

	@Before
	public void setUp() {
		tm = new TimerMetric("test") {};
	}

	@Test
	public void test() throws InterruptedException {
		assertEquals("test: 0", tm.toString());
		tm.start();
		assertTrue(Long.parseLong(tm.toString().split(" ")[1]) - System.currentTimeMillis() < 1000);
		Thread.sleep(10);
		tm.stop();
		long val = Long.parseLong(tm.toString().split(" ")[1]);
		assertTrue(val > 0);
		assertTrue(val < 1000);
	}
}
