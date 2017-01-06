package fr.unice.polytech.si3.miaou.brainfuck.metrics;

/**
 * ValueMetric stores the brainfuck program size.
 *
 * @author Julien Lemaire
 * @author Pierre-Emmanuel Novac
 */
public class ProgSize extends ValueMetric {
	/**
	 * Displayed name.
	 */
	public static final String NAME = "PROG_SIZE";

	/**
	 * Creates a new ProgSize metric.
	 */
	ProgSize() {
		super(NAME);
	}
}
