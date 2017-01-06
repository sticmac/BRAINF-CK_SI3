package fr.unice.polytech.si3.miaou.brainfuck.metrics;

/**
 * CounterMetric increments a counter for each memory read.
 *
 * @author Julien Lemaire
 * @author Pierre-Emmanuel Novac
 */
public class DataRead extends CounterMetric {
	/**
	 * Displayed name.
	 */
	public static final String NAME = "DATA_READ";

	/**
	 * Creates a new DataRead metric.
	 */
	DataRead() {
		super(NAME);
	}
}
