package fr.unice.polytech.si3.miaou.brainfuck.metrics;

/**
 * CounterMetric increments a counter for each memory write.
 *
 * @author Julien Lemaire
 * @author Pierre-Emmanuel Novac
 */
public class DataWrite extends CounterMetric {
	/**
	 * Displayed name.
	 */
	public static final String NAME = "DATA_WRITE";

	/**
	 * Creates a new DataWrite metric.
	 */
	DataWrite() {
		super(NAME);
	}
}
