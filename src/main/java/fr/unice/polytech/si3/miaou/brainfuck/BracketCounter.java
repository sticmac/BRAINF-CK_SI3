package fr.unice.polytech.si3.miaou.brainfuck;

/**
 * Counts brackets and calls callback function upon match or mismatch.
 * Matching callback is called when the right and left counter are equals after incrementing one or the other.
 * Mismatching callback is called by checkMatching() when counters are not equal or after incrementing the right counter if it is found to be greater than the left.
 *
 * @author Pierre-Emmanuel Novac
 */
public class BracketCounter {
	/**
	 * Counter for left brackets.
	 */
	private int left = 0;

	/**
	 * Counter for right brackets.
	 */
	private int right = 0;

	/**
	 * Constructs a new BracketCounter.
	 */
	public BracketCounter() {
	}

	/**
	 * Reset counters to 0
	 */
	protected void reset() {
		left = 0;
		right = 0;
	}

	/**
	 * Called upon finding the matching right (respectively left) bracket to a left (respectively right).
	 * May be overriden by child class.
	 */
	protected void onMatch() {
	}

	/**
	 * Called upon finding too much right brackets while parsing or not enough right brackets at the end.
	 * May be overriden by a child class.
	 */
	protected void onMismatch() {
	}

	/**
	 * Checks if left and right bracket count match.
	 * Used at the end of parsing to call onMismatch() if not enough right brackets were found.
	 */
	public void checkMatching() {
		if (left != right) onMismatch();
	}

	/**
	 * Increments the counter of left brackets.
	 * Calls onMatch(à if there are as much right brackets as left brackets.
	 */
	public void incrLeft() {
		left++;
		if (left == right) onMatch();
	}

	/**
	 * Increments the counter of right brackets.
	 * Calls onMatch() if there are as much right brackets as left brackets.
	 * Calls onMismatch() if too much right brackets were found.
	 */
	public void incrRight() {
		right++;
		if (left == right) onMatch();
		else if (right > left) onMismatch();
	}
}
