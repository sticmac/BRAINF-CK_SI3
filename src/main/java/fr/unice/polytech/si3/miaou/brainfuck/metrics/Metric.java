package fr.unice.polytech.si3.miaou.brainfuck.metrics;

/**
 * Metric superclass for polymorphims in Metrics container.
 *
 * @author Pierre-Emmanuel Novac
 */
public abstract class Metric {
	private String name;

	/**
	 * Creates a new Metric with the given name.
	 *
	 * @param name	metric name.
	 */
	Metric(String name) {
		this.name = name;
	}

	/**
	 * Returns the metric name.
	 *
	 * @return metric name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the metric name and given value as a String.
	 *
	 * @param value	metric value.
	 * @return metric name and value as a String.
	 */
	public <T> String toString(T value) {
		return name + ": " + value;
	}
}
