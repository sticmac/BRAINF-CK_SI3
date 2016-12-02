package fr.unice.polytech.si3.miaou.brainfuck;

/**
 * Define the different Metrics for the current instance of the program.
 *
 * @author Julien Lemaire
 */
public enum Metrics {
	PROG_SIZE, EXEC_TIME, EXEC_MOVE, DATA_MOVE, DATA_WRITE, DATA_READ;

	/**
	 * The value of the current Metric.
	 */
	private long value;

	/**
	 * Increment the value by one.
	 */
	public void incr() {
		this.value++;
	}

	/**
	 * Set the value of the metric with a new long value.
	 *
	 * @param value the new value of the metrics.
	 */
	public void set(long value) {
		this.value = value;
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
	 * Get the value of the metric.
	 *
	 * @return the value of the metric.
	 */
	public long value() {
		return value;
	}

	/**
	 * Dumps the metrics content as a String.
	 *
	 * @return Metrics as a String.
	 */
	public static String dump() {
		String req = "";
		for (Metrics m : Metrics.values()) {
			req += m.name() + ": " + m.value() + "\n";
		}
		return req;
	}
}
