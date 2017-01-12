package fr.unice.polytech.si3.miaou.brainfuck.metrics;

/**
 * TimerMetric calculates the execution duration.
 *
 * @author Julien Lemaire
 * @author Pierre-Emmanuel Novac
 */
public class ExecTime extends TimerMetric {
	/**
	 * Displayed name.
	 */
	public static final String NAME = "EXEC_TIME";

	/**
	 * Creates a new ExecTime metric.
	 */
	ExecTime() {
		super(NAME);
	}
}
