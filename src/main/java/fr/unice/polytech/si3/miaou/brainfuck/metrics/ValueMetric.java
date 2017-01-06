package fr.unice.polytech.si3.miaou.brainfuck.metrics;

/**
 * ValueMetric stores a value.
 *
 * @author Pierre-Emmanuel Novac
 */
abstract class ValueMetric extends Metric {
	/**
	 * The value of the current Metric.
	 */
	private int value;

	/**
	 * Creates a new ValueMetric with the given name.
	 *
	 * @param name	metric name.
	 */
	ValueMetric(String name) {
		super(name);
	}

	/**
	 * Set the value of the metric with a new long value.
	 *
	 * @param value the new value of the metrics.
	 */
	public void set(int value) {
		this.value = value;
	}

	/**
	 * Returns the metric name and value as a String.
	 *
	 * @return metric name and value as a String.
	 */
	@Override
	public String toString() {
		return toString(value);
	}
}
