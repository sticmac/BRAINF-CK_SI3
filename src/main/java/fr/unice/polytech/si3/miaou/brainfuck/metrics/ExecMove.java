package fr.unice.polytech.si3.miaou.brainfuck.metrics;

/**
 * CounterMetric increments a counter for each move in the instructions' list.
 *
 * @author Julien Lemaire
 * @author Pierre-Emmanuel Novac
 */
public class ExecMove extends CounterMetric {
	/**
	 * Displayed name.
	 */
	public static final String NAME = "EXEC_MOVE";

	/**
	 * Creates a new ExecMove metric.
	 */
	ExecMove() {
		super(NAME);
	}
}
