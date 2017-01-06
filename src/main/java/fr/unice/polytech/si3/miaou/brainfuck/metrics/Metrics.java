package fr.unice.polytech.si3.miaou.brainfuck.metrics;

import java.util.Map;
import java.util.HashMap;

import java.util.stream.Collectors;

/**
 * Define the different Metrics for the current instance of the program.
 * Typesafe heterogeneous container pattern.
 *
 * @author Julien Lemaire
 * @author Pierre-Emmanuel Novac
 */
public class Metrics {
	/**
	 * Typesafe heterogeneous container.
	 */
	private Map<Class<? extends Metric>, Metric> metricsMap;

	/**
	 * Creates a new Metrics container.
	 */
	public Metrics() {
		metricsMap = new HashMap<>();
		metricsMap.put(ProgSize.class, new ProgSize());
		metricsMap.put(DataRead.class, new DataRead());
		metricsMap.put(DataWrite.class, new DataWrite());
		metricsMap.put(DataMove.class, new DataMove());
		metricsMap.put(ExecMove.class, new ExecMove());
		metricsMap.put(ExecTime.class, new ExecTime());
	}

	/**
	 * Get a metric.
	 *
	 * @param clazz	class of the metric to fetch.
	 * @return metric object with the given class casted to its dynamic type.
	 */
	public <T extends Metric> T get(Class<T> clazz) {
		return clazz.cast(metricsMap.get(clazz));
	}

	/**
	 * Dumps the metrics content as a String.
	 *
	 * @return Metrics as a String.
	 */
	@Override
	public String toString() {
		return metricsMap.values().stream().map(Metric::toString).collect(Collectors.joining("\n")) + "\n";
	}
}
