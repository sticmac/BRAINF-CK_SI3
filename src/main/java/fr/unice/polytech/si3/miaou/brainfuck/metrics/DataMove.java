package fr.unice.polytech.si3.miaou.brainfuck.metrics;

/**
 * DataMove increments a counter for each shift of the memory pointer.
 *
 * @author Julien Lemaire
 * @author Pierre-Emmanuel Novac
 */
public class DataMove extends CounterMetric {
	/**
	 * Displayed name.
	 */
	public static final String NAME = "DATA_MOVE";

	/**
	 * Creates a new DataMove metric.
	 */
	DataMove() {
		super(NAME);
	}
}
