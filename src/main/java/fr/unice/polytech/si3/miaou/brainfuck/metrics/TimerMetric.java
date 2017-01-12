package fr.unice.polytech.si3.miaou.brainfuck.metrics;

/**
 * TimerMetric calculates a duration.
 *
 * @author Pierre-Emmanuel Novac
 */
abstract class TimerMetric extends Metric {
	/**
	 * The value of the current Metric.
	 */
	private long value;

	/**
	 * Creates a new TimerMetric with the given name.
	 *
	 * @param name	metric name.
	 */
	TimerMetric(String name) {
		super(name);
	}

	/**
	 * Start a timer.
	 */
	public void start() {
		this.value = System.currentTimeMillis();
	}

	/**
	 * Stop the previously started timer and calculate the time difference with the start.
	 */
	public void stop() {
		this.value = System.currentTimeMillis() - this.value;
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
