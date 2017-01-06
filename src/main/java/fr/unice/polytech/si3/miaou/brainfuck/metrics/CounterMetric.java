package fr.unice.polytech.si3.miaou.brainfuck.metrics;

/**
 * CounterMetric increments a counter for each call.
 *
 * @author Pierre-Emmanuel Novac
 */
abstract class CounterMetric extends Metric {
	/**
	 * The value of the current Metric.
	 */
	private int value = 0;

	/**
	 * Creates a new CounterMetric with the given name.
	 *
	 * @param name	metric name.
	 */
	CounterMetric(String name) {
		super(name);
	}

	/**
	 * Increment the value by one.
	 */
	public void incr() {
		this.value++;
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
